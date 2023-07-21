package com.example.fileloader.exceptions.handler;

import com.example.fileloader.exceptions.FileNotFoundException;
import com.example.fileloader.exceptions.FileUploadException;
import com.example.fileloader.exceptions.GetFileException;
import com.example.fileloader.exceptions.StorageNotFoundException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.fileloader.exceptions.messages.ExceptionMessages.*;

@ControllerAdvice
public class FileServiceExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> fileUploadHandle(FileUploadException e) {
        String errorMessage = formatMessage(FILE_UPLOAD_ERROR, e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(errorMessage)
            .status(500)
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(StorageNotFoundException.class)
    public ResponseEntity<ErrorResponse> storageNotFoundHandle(StorageNotFoundException e) {
        String errorMessage = formatMessage(STORAGE_NOT_FOUND, e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(errorMessage)
            .status(404)
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(GetFileException.class)
    public ResponseEntity<ErrorResponse> getFileExceptionHandle(GetFileException e) {
        String errorMessage = formatMessage(FILE_GET_ERROR, e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(errorMessage)
            .status(500)
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ErrorResponse> fileNotFoundHandle(FileNotFoundException e) {
        String errorMessage = formatMessage(FILE_NOT_FOUND, e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
            .message(errorMessage)
            .status(404)
            .build();
        return ResponseEntity.status(404).body(errorResponse);
    }

    @Builder
    private record ErrorResponse(int status, String message) { }
}
