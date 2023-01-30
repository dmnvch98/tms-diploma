package com.example.userservice.handler;

import com.example.userservice.exceptions.StudentCannotBeDeletedException;
import com.example.userservice.exceptions.TutorCannotBeDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(TutorCannotBeDeletedException.class)
    public ResponseEntity<String> handleException(TutorCannotBeDeletedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(StudentCannotBeDeletedException.class)
    public ResponseEntity<String> handleException(StudentCannotBeDeletedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }
}
