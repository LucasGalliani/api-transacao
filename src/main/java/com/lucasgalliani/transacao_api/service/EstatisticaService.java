package com.lucasgalliani.transacao_api.service;


import com.lucasgalliani.transacao_api.dto.EstatisticaDTO;
import com.lucasgalliani.transacao_api.dto.TransacaoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    @Autowired
    private TransacaoService transacaoService;


    public EstatisticaDTO calcularEstatisticasTransaca(Integer intervaloDeBusca) {

        log.info("Iniciando o calculo de estatistica da transacao..");

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(intervaloDeBusca);

        if (transacoes.isEmpty()) {

            return new EstatisticaDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatistica = transacoes.stream()
                .mapToDouble(TransacaoDTO::valor)
                .summaryStatistics();

        return new EstatisticaDTO(estatistica.getCount(), estatistica.getSum(), estatistica.getAverage(), estatistica.getMin(), estatistica.getMax());
    }

}
