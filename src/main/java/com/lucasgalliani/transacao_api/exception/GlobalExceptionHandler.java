package com.lucasgalliani.transacao_api.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<String> handlerUnprocessableEntity(UnprocessableEntity ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("Erro: " + ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception ex) {


        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro: " + ex.getMessage());
    }
}