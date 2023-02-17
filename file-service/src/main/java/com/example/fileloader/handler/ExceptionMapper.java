package com.example.fileloader.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handeException(final Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
