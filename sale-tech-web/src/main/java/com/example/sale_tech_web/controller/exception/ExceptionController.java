package com.example.sale_tech_web.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<String> clientException(ClientException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<String> serverException(ServerException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
