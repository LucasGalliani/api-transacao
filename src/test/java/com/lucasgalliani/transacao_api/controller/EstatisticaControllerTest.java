package com.lucasgalliani.transacao_api.controller;

import com.lucasgalliani.transacao_api.domain.dto.EstatisticaDTO;
import com.lucasgalliani.transacao_api.service.EstatisticaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EstatisticaControllerTest {

    @InjectMocks
    EstatisticaController estatisticaController;

    @Mock
    EstatisticaService estatisticaService;

    EstatisticaDTO estatisticaDTO;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatisticaDTO = new EstatisticaDTO(1L,20.0,20.0,20.0,20.0);
    }

    @Test
    void buscarEstatistica() throws Exception {

        when(estatisticaService.calcularEstatisticasTransaca(60)).thenReturn(estatisticaDTO);

        mockMvc.perform(get("/estatistica")
                        .param("intervaloBusca", "60")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatisticaDTO.count()));
    }
}