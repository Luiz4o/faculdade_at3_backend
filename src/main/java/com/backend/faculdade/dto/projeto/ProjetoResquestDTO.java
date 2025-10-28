package com.backend.faculdade.dto.projeto;

import java.time.OffsetDateTime;
import java.util.List;

public record ProjetoResquestDTO(
        String descricao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim,
        List<String> funcionarios
) {
}
