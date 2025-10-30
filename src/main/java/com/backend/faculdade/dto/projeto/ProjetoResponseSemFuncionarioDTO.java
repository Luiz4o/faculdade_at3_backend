package com.backend.faculdade.dto.projeto;

import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;

import java.time.OffsetDateTime;
import java.util.List;

public record ProjetoResponseSemFuncionarioDTO (
        Long id,
        String descricao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim
) {
    public ProjetoResponseSemFuncionarioDTO (Projeto p){
        this(p.getId(),p.getDescricao(),p.getDataInicio(),p.getDataFim());
    }}
