package com.backend.faculdade.service.impl;

import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResquestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.exception.ResourceNotFoundException;
import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;
import com.backend.faculdade.model.Setor;
import com.backend.faculdade.repository.FuncionarioRepository;
import com.backend.faculdade.repository.ProjetoFuncionarioRepository;
import com.backend.faculdade.repository.ProjetoRepository;
import com.backend.faculdade.service.ProjetoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProjetoFuncionarioRepository projetoFuncionarioRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository, ProjetoFuncionarioRepository projetoFuncionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.projetoFuncionarioRepository = projetoFuncionarioRepository;
    }

    @Override
    public ProjetoResponseDTO findById(Long id) {
        var projeto = projetoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Projeto", id));
        return new ProjetoResponseDTO(projeto);
    }

    @Override
    public List<ProjetoResponseDTO> findAll() {
        var listProjetos = projetoRepository.findAll();

        return listProjetos.stream()
                .map(ProjetoResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public ProjetoResponseDTO create(ProjetoResquestDTO projetoResquestDTO) {
        var projeto = projetoRepository.save(new Projeto(projetoResquestDTO));


        if (projetoResquestDTO.funcionarios() != null && !projetoResquestDTO.funcionarios().isEmpty()) {
            for (String nomeFuncionario : projetoResquestDTO.funcionarios()) {

                var funcionario = Funcionario.builder()
                        .nome(nomeFuncionario.trim())
                        .build();
                funcionario = funcionarioRepository.save(funcionario);

                var relacao = new ProjetoFuncionario(projeto, funcionario);
                projeto.getFuncionarios().add(relacao);
            }
        }

        projeto = projetoRepository.save(projeto);
        return new ProjetoResponseDTO(projeto);
    }

    @Override
    @Transactional
    public ProjetoResponseDTO addFuncionario(Long idProjeto, Long idFuncionario) {
        var projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(()-> new ResourceNotFoundException("Projeto", idProjeto));

        var funcionaro = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", idFuncionario));

        projetoFuncionarioRepository.save(new ProjetoFuncionario(projeto,funcionaro));

        projeto = projetoRepository.findById(idProjeto).get();

        return new ProjetoResponseDTO(projeto);
    }

    @Override
    public ProjetoResponseDTO addListFuncionarios(Long idProjeto, List<Long> idFuncionarios) {
        return null;
    }

    @Override
    public void deleteFuncionario(Long idProjeto, Long idFuncionario) {
        var projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(()-> new ResourceNotFoundException("Projeto", idProjeto));

        var funcionaro = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", idFuncionario));

        projeto.removeFuncionario(funcionaro);
        projetoRepository.save(projeto);
    }

    @Override
    public void deleteListFuncionario(Long idProjeto, List<Long> idFuncionarios) {

    }

    @Override
    @Transactional
    public ProjetoResponseDTO update(Long id, ProjetoResquestDTO dto) {
        var projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto", id));

        if (dto.descricao() != null && !dto.descricao().isBlank()) {
            projeto.setDescricao(dto.descricao());
        }

        OffsetDateTime novoInicio = dto.dataInicio();
        OffsetDateTime novoFim = dto.dataFim();

        if (novoInicio != null && novoFim != null) {
            if (novoFim.isBefore(novoInicio)) {
                throw new IllegalArgumentException("dataFim não pode ser anterior a dataInicio");
            }
            projeto.setDataInicio(novoInicio);
            projeto.setDataFim(novoFim);
        } else if (novoInicio != null) {
            var fimAtual = projeto.getDataFim();
            if (fimAtual != null && fimAtual.isBefore(novoInicio)) {
                throw new IllegalArgumentException("dataInicio não pode ser posterior à dataFim atual");
            }
            projeto.setDataInicio(novoInicio);
        } else if (novoFim != null) {
            var inicioAtual = projeto.getDataInicio();
            if (inicioAtual != null && novoFim.isBefore(inicioAtual)) {
                throw new IllegalArgumentException("dataFim não pode ser anterior à dataInicio atual");
            }
            projeto.setDataFim(novoFim);
        }

        projeto = projetoRepository.save(projeto);
        return new ProjetoResponseDTO(projeto);
    }


    @Override
    public void delete(Long id) {
        var projeto = projetoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Projeto", id));
        projetoRepository.delete(projeto);
    }
}
