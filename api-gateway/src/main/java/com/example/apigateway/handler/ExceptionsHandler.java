package com.example.apigateway.handler;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e) {
        return ResponseEntity.status(403).body(e.contentUTF8());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException() {
        return ResponseEntity.internalServerError().build();
    }

}
