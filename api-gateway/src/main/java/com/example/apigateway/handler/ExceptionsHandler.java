package com.example.apigateway.handler;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleException(FeignException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.responseBody().toString());
    }
}