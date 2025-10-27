package com.backend.faculdade.service.impl;

import com.backend.faculdade.dto.funcionario.FuncionarioRequestDTO;
import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.exception.ResourceNotFoundException;
import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.repository.FuncionarioRepository;
import com.backend.faculdade.service.FuncionarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {
        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", id));
        return new FuncionarioResponseDTO(funcionario);
    }

    @Override
    public List<FuncionarioResponseDTO> findAll() {
        var funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    @Override
    public FuncionarioResponseDTO create(FuncionarioRequestDTO funcionarioRequestDTO) {
        var funcionario = Funcionario.builder()
                .nome(funcionarioRequestDTO.nome())
                .build();
        funcionario = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    @Override
    public FuncionarioResponseDTO update(Long id,FuncionarioRequestDTO funcionarioRequestDTO) {
        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", id));

        if(!Objects.isNull(funcionarioRequestDTO.nome())){
            funcionario.setNome(funcionarioRequestDTO.nome());
        }
        funcionario = funcionarioRepository.save(funcionario);
        return new FuncionarioResponseDTO(funcionario);
    }

    @Override
    public void delete(Long id) {
        var funcionario = funcionarioRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionário", id));

        funcionarioRepository.delete(funcionario);
    }
}
