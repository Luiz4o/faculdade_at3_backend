package com.backend.faculdade.dto.funcionario;

import com.backend.faculdade.model.Funcionario;
import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;
import com.backend.faculdade.model.Setor;
import com.backend.faculdade.service.FuncionarioService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public record FuncionarioResponseDTO (
        Long id,
        String nome,
        Setor setor,
        List<Projeto> projetos
){
    public FuncionarioResponseDTO (Funcionario f){
        this(f.getId(),f.getNome(),f.getSetor(),f.getProjetos() != null
                ? f.getProjetos().stream()
                .map(ProjetoFuncionario::getProjeto)
                .toList()
                : List.of()
        );
    }

}
