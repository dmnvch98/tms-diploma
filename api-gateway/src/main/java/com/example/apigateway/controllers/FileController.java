package com.example.apigateway.controllers;

import com.example.apigateway.dto.StorageDto;
import com.example.apigateway.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStorage(@RequestBody final StorageDto storage) {
        fileService.createStorage(storage);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
