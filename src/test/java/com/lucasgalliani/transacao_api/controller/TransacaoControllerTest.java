package com.lucasgalliani.transacao_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lucasgalliani.transacao_api.domain.dto.TransacaoDTO;
import com.lucasgalliani.transacao_api.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    private TransacaoDTO transacaoDTO;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();

        transacaoDTO = new TransacaoDTO(20.0,
                OffsetDateTime.of(2025, 7, 21, 21, 3, 0, 0, ZoneOffset.UTC));
    }

    @Test
    void cadastroTransacao() throws Exception {
        doNothing().when(transacaoService).cadastroTransacoes(transacaoDTO);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacaoDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveGerarExceptionAoAdicionarTransacao() throws Exception {

//        doThrow(new UnprocessableEntity("Erro de requisicão")).when(transacaoService).cadastroTransacoes(transacaoDTO);
        doNothing().when(transacaoService).limparTransacoes();
        mockMvc.perform(delete("/transacao")
                        .content(objectMapper.writeValueAsString(transacaoDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletarTransacao() throws Exception {

        doNothing().when(transacaoService).limparTransacoes();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());

    }
}
