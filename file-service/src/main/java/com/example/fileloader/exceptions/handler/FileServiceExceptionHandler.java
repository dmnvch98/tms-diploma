package com.example.fileloader.exceptions.handler;

import com.example.fileloader.exceptions.FileUploadException;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileServiceExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> fileNotFoundHandle(FileUploadException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().message("An error occurred while uploading the file.").build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Builder
    @Value
    private static class ErrorResponse {
        String message;
    }
}
