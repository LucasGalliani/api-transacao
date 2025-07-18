package com.lucasgalliani.transacao_api.service;


import com.lucasgalliani.transacao_api.dto.TransacaoDTO;
import com.lucasgalliani.transacao_api.exception.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {


    private final List<TransacaoDTO> listaTransacao = new ArrayList<>();


    public void cadastroTransacoes(TransacaoDTO dto) {

        log.info("Iniciado o processamento de gravar transcações.");

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {

            log.error("Data e hora maiores que a data hora atual.");
            throw new UnprocessableEntity("Data e hora maiores que a data hora atual.");
        }

        if(dto.valor() < 0){

            log.error("Valor esta vindo negativo.");
            throw new UnprocessableEntity("Valor está vindo negativo.");
        }

        listaTransacao.add(dto);

    }

    public void limparTransacoes(){

        log.info("Limpando as transações.");

        listaTransacao.clear();
    }

    public List<TransacaoDTO> buscarTransacoes(Integer intervaloHora){

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloHora);

        log.info("Iniciando o processo de buscar de transações.");

        return listaTransacao.stream()
                .filter(t -> t.dataHora().isAfter(dataHoraIntervalo))
                .collect(Collectors.toList());
    }
}
