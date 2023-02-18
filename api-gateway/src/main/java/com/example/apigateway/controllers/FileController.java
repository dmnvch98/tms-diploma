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
    public ResponseEntity<String> uploadFile(@RequestPart("file") final MultipartFile file, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileService.uploadFile(file, userId));
    }

    @GetMapping("/avatar/{fileName}")
    public ResponseEntity<String> getFile(@PathVariable String fileName) {
        return ResponseEntity.ok(fileService.getFile(fileName));
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<Boolean> deleteFile(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileService.deleteFile(userId));
    }

    @GetMapping("/default-avatar")
    public ResponseEntity<String> getDefaultAvatar() {
        return ResponseEntity.ok(fileService.getFile("default_avatar.png"));
    }
}
