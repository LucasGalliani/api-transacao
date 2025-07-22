package com.lucasgalliani.transacao_api.service;

import com.lucasgalliani.transacao_api.domain.dto.EstatisticaDTO;
import com.lucasgalliani.transacao_api.domain.dto.TransacaoDTO;
import com.lucasgalliani.transacao_api.exception.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoServiceTest {


    TransacaoService transacaoService;

    TransacaoDTO transacaoDTO;

    EstatisticaDTO estatisticaDTO;

    @BeforeEach
    void setUp(){
        transacaoService = new TransacaoService();
        transacaoDTO = new TransacaoDTO(20.0, OffsetDateTime.now());
        estatisticaDTO = new EstatisticaDTO(1L,20.0,20.0,20.0,20.0);
    }


    @Test
    void deveLancarExceptionCasoValorSejaNegativo(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.cadastroTransacoes(new TransacaoDTO(-10.0,OffsetDateTime.now())));
        assertEquals("Valor está vindo negativo.",exception.getMessage());
    }

    @Test
    void deveLancarExceptionCasoDataHoraMaiorQueAtual(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.cadastroTransacoes(new TransacaoDTO(-10.0,OffsetDateTime.now().plusDays(1))));
        assertEquals("Data e hora maiores que a data hora atual.",exception.getMessage());
    }

    @Test
    void cadastroTransacoes() {

        transacaoService.cadastroTransacoes(transacaoDTO);

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacaoDTO));

    }

    @Test
    void limparTransacoes() {

        transacaoService.limparTransacoes();

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());

    }

    @Test
    void buscarTransacoes() {

        TransacaoDTO dto = new TransacaoDTO(10.00,OffsetDateTime.now().minusHours(1));

        transacaoService.cadastroTransacoes(transacaoDTO);
        transacaoService.cadastroTransacoes(dto);

        List<TransacaoDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacaoDTO));
        assertFalse(transacoes.contains(dto));

    }

}