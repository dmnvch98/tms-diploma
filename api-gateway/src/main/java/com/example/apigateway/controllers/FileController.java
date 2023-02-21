package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.FileDto;
import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.facades.FileFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping(value = "/")
    public ResponseEntity<String> uploadFile(@RequestPart("file") final MultipartFile file, Authentication authentication) throws IOException {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileFacade.uploadFile(file.getInputStream(), userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/avatar/{userId}")
    public String getFile(@PathVariable Long userId) {
        return fileFacade.getFile(userId);
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<Boolean> deleteFile(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileFacade.deleteFile(userId));
    }

    @GetMapping("/default-avatar")
    public ResponseEntity<String> getDefaultAvatar() {
        return ResponseEntity.ok(fileFacade.getDefaultAvatar());
    }
}
