package com.example.fileloader.handler;

import com.example.fileloader.dto.ResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;


@ControllerAdvice
public class ExceptionMapper {
    @ExceptionHandler(IOException.class)
    public ResponseDto handeException() {
        return ResponseDto.builder().message("Some error occurred").build();
    }
}
