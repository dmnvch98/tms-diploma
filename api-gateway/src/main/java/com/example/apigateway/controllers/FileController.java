package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.facades.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping(value = "/")
    public ResponseDto uploadFile(@RequestPart("file") final MultipartFile file, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.uploadFile(file, userId);
    }

    @GetMapping("/avatar/{userId}")
    public ResponseDto getFile(@PathVariable Long userId) {
        return fileFacade.getFile(userId);
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<Boolean> deleteFile(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileFacade.deleteFile(userId));
    }

    @GetMapping("/default-avatar")
    public ResponseDto getDefaultAvatar() {
        return fileFacade.getDefaultAvatar();
    }
}
