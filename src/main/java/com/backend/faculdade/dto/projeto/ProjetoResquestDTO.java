package com.backend.faculdade.dto.projeto;

import java.time.OffsetDateTime;

public record ProjetoResquestDTO(
        String descricao,
        OffsetDateTime dataInicio,
        OffsetDateTime dataFim
) {
}
