package com.lucasgalliani.transacao_api.controller;

import com.lucasgalliani.transacao_api.dto.EstatisticaDTO;
import com.lucasgalliani.transacao_api.service.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatisticaService estatisticaService;


    @GetMapping
    @Operation(description = "Endpoint responsável por buscar transações")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200",description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro na busca de estatisticas de transções"),
            @ApiResponse(responseCode = "500",description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticaDTO> buscarEstatistica(@RequestParam(value = "intervaloBusca",required = false,defaultValue = "60") Integer intervalo){

        return ResponseEntity.ok(estatisticaService.calcularEstatisticasTransaca(intervalo));
    }

}
