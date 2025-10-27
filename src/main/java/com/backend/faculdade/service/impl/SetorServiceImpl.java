package com.backend.faculdade.service.impl;

import com.backend.faculdade.dto.setor.SetorRequestDTO;
import com.backend.faculdade.dto.setor.SetorResponseDTO;
import com.backend.faculdade.exception.ResourceNotFoundException;
import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Setor;
import com.backend.faculdade.repository.FuncionarioRepository;
import com.backend.faculdade.repository.SetorRepository;
import com.backend.faculdade.service.SetorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;
    private final FuncionarioRepository funcionarioRepository;

    public SetorServiceImpl(SetorRepository setorRepository, FuncionarioRepository funcionarioRepository) {
        this.setorRepository = setorRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public SetorResponseDTO findById(Long id) {
        var setor =  setorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor", id));

        return new SetorResponseDTO(setor);
    }

    @Override
    public List<SetorResponseDTO> findAll() {
        var listSetor = setorRepository.findAll();

        return listSetor.stream()
                .map(SetorResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional
    public SetorResponseDTO create(SetorRequestDTO setorRequestDTO) {
        var setor = setorRepository.save(new Setor(setorRequestDTO));
        return new SetorResponseDTO(setor);
    }

    @Override
    @Transactional
    public SetorResponseDTO addFuncionario(Long idSetor, Long idFuncionario) {
        var setor = setorRepository.findById(idSetor)
                .orElseThrow(()-> new ResourceNotFoundException("Setor", idSetor));

        var funcionaro = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", idFuncionario));

        setor.addFuncionario(funcionaro);
        setor = setorRepository.save(setor);
        return new SetorResponseDTO(setor);
    }

    @Override
    public SetorResponseDTO addListFuncionarios(Long idSetor, List<Long> idFuncionarios) {
        return null;
    }

    @Override
    @Transactional
    public void deleteFuncionario(Long idSetor, Long idFuncionario) {
        var setor = setorRepository.findById(idSetor)
                .orElseThrow(()-> new ResourceNotFoundException("Setor", idSetor));

        var funcionaro = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", idFuncionario));

        setor.removeFuncionario(funcionaro);
        setorRepository.save(setor);
    }

    @Override
    public void deleteListFuncionario(Long idSetor, List<Long> idFuncionarios) {

    }

    @Override
    public SetorResponseDTO update(SetorRequestDTO setorRequestDTO) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var setor = setorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Setor", id));
        setorRepository.delete(setor);
    }
}
