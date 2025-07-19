package com.lucasgalliani.transacao_api.controller;


import com.lucasgalliani.transacao_api.dto.TransacaoDTO;
import com.lucasgalliani.transacao_api.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201",description = "Transacao gravada com sucesso"),
            @ApiResponse(responseCode = "422",description = "Campos não atendem os requisitos da transação"),
            @ApiResponse(responseCode = "400",description = "Erro de requisição"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")
    })
    public ResponseEntity<TransacaoDTO> cadastroTransacao(@RequestBody TransacaoDTO dto){

       transacaoService.cadastroTransacoes(dto);

       return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200",description = "Transacao deletadas com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro de requisição"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")
    })
    public ResponseEntity<TransacaoDTO> deletarTransacao(TransacaoDTO dto){

        transacaoService.limparTransacoes();

        return ResponseEntity.ok().build();
    }

}
