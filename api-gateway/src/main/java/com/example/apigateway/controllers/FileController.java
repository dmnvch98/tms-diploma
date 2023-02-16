package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/")
    public void uploadFile(@RequestPart("file") final MultipartFile file, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        fileService.uploadFile(file, userId);
    }

    @GetMapping("/")
    public ResponseEntity<String> getFile(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileService.getFile(userId + "_avatar.png"));
    }
}
