package com.backend.faculdade.dto.setor;

import com.backend.faculdade.dto.funcionario.FuncionarioResponseDTO;
import com.backend.faculdade.dto.funcionario.FuncionarioResponseSetorDTO;
import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Setor;

import java.util.List;

public record SetorResponseDTO(
        Long id,
        String nome,
        List<FuncionarioResponseSetorDTO> funcionarios
){

    public SetorResponseDTO(Setor setor) {
        this(
                setor.getId(),
                setor.getNome(),
                setor.getFuncionarios() != null
                        ? setor.getFuncionarios().stream()
                        .map(FuncionarioResponseSetorDTO::new)
                        .toList()
                        : List.of()
        );
    }
}
