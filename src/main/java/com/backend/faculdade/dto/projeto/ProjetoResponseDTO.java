package com.backend.faculdade.dto.projeto;

import com.backend.faculdade.model.Projeto;
import com.backend.faculdade.model.ProjetoFuncionario;
import jakarta.persistence.OneToMany;

import java.time.OffsetDateTime;
import java.util.List;

public record ProjetoResponseDTO(
        Long id,
        String descricao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim,
        List<ProjetoFuncionario> funcionarios
) {
    public ProjetoResponseDTO (Projeto p){
        this(p.getId(),p.getDescricao(),p.getDataInicio(),p.getDataFim(),p.getFuncionarios());
    }
}
