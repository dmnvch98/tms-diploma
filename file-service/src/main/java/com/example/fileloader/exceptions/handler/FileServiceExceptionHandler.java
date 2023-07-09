package com.example.fileloader.exceptions.handler;

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
        ErrorResponse errorResponse = ErrorResponse.builder().message(FILE_UPLOAD_ERROR).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(StorageNotFoundException.class)
    public ResponseEntity<ErrorResponse> storageNotFoundHandle(StorageNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(STORAGE_NOT_FOUND + e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(GetFileException.class)
    public ResponseEntity<ErrorResponse> getFileExceptionHandle() {
        ErrorResponse errorResponse = ErrorResponse.builder().message(FILE_GET_ERROR).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Builder
    private record ErrorResponse(String message) {
    }
}
