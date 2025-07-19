package com.lucasgalliani.transacao_api.domain.dto;

import java.time.OffsetDateTime;

public record TransacaoDTO(Double valor,
                           OffsetDateTime dataHora) {
}
