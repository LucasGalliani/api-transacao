package com.lucasgalliani.transacao_api.domain.dto;

public record EstatisticaDTO(Long count,
                             Double sum,
                             Double avg,
                             Double min,
                             Double max) {


}
