package com.lucasgalliani.transacao_api.controller;


import com.lucasgalliani.transacao_api.dto.TransacaoDTO;
import com.lucasgalliani.transacao_api.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    public ResponseEntity<TransacaoDTO> cadastroTransacao(@RequestBody TransacaoDTO dto){

       transacaoService.cadastroTransacoes(dto);

       return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    public ResponseEntity<TransacaoDTO> deletarTransacao(TransacaoDTO dto){

        transacaoService.limparTransacoes();

        return ResponseEntity.ok().build();
    }

}
