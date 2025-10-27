package com.backend.faculdade.dto.setor;

import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Setor;

import java.util.List;

public record SetorResponseDTO(
        Long id,
        String nome,
        List<Funcionario> funcionarios
){
    public SetorResponseDTO (Setor s){
        this(s.getId(), s.getNome(), s.getFuncionarios());
    }
}
