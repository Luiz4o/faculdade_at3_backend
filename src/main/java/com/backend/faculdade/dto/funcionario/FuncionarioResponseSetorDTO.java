package com.backend.faculdade.dto.funcionario;

import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;
import com.backend.faculdade.model.Setor;

import java.util.List;

public record FuncionarioResponseSetorDTO (
        Long id,
        String nome,
        List<Projeto> projetos
){
    public FuncionarioResponseSetorDTO (Funcionario f){
        this(f.getId(),f.getNome(),f.getProjetos() != null
                ? f.getProjetos().stream()
                .map(ProjetoFuncionario::getProjeto)
                .toList()
                : List.of()
        );
    }

}
