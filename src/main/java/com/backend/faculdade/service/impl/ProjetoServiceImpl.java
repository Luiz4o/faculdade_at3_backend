package com.backend.faculdade.service.impl;

import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResponseDTO;
import com.backend.faculdade.dto.projeto.ProjetoResquestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.exception.ResourceNotFoundException;
import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;
import com.backend.faculdade.model.Setor;
import com.backend.faculdade.repository.FuncionarioRepository;
import com.backend.faculdade.repository.ProjetoFuncionarioRepository;
import com.backend.faculdade.repository.ProjetoRepository;
import com.backend.faculdade.service.ProjetoService;

import java.util.List;

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
    public ProjetoResponseDTO create(ProjetoResquestDTO projetoResquestDTO) {
        var projeto = projetoRepository.save(new Projeto(projetoResquestDTO));
        return new ProjetoResponseDTO(projeto);
    }

    @Override
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
    public ProjetoResponseDTO update(ProjetoResquestDTO projetoRequestDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        var projeto = projetoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor", id));
        projetoRepository.delete(projeto);
    }
}
