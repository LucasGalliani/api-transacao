package com.lucasgalliani.transacao_api.service;

import com.lucasgalliani.transacao_api.domain.dto.EstatisticaDTO;
import com.lucasgalliani.transacao_api.domain.dto.TransacaoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoDTO transacaoDTO;

    EstatisticaDTO estatisticaDTO;

    @BeforeEach
    void setUp(){
        transacaoDTO = new TransacaoDTO(20.0,OffsetDateTime.now());
        estatisticaDTO = new EstatisticaDTO(1L,20.0,20.0,20.0,20.0);
    }

    @Test
    void calcularEstatisticasComSucesso(){
        when(transacaoService.buscarTransacoes(60))
                .thenReturn(Collections.singletonList(transacaoDTO));

        EstatisticaDTO resultado = estatisticaService.calcularEstatisticasTransaca(60);
        verify(transacaoService, times(1))
                .buscarTransacoes(60);

        EstatisticaDTO estatisticaEsperada = new EstatisticaDTO(
                1L, // count
                20.0, // sum
                20.0, // avg
                20.0, // max
                20.0  // min
        );

        verify(transacaoService,times(1)).buscarTransacoes(60);

        assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaDTO);


        assertThat(resultado)
                .usingRecursiveComparison()
                .isEqualTo(estatisticaEsperada);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia(){

        EstatisticaDTO estatisticasEsperado = new EstatisticaDTO(0L,0.0,0.0,0.0,0.0);

        when(transacaoService.buscarTransacoes(60))
                .thenReturn(Collections.emptyList());

        EstatisticaDTO resultado = estatisticaService.calcularEstatisticasTransaca(60);

        verify(transacaoService, times(1))
                .buscarTransacoes(60);


        assertThat(resultado)
                .usingRecursiveComparison()
                .isEqualTo(estatisticasEsperado);
    }

}
