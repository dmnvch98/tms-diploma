package com.example.fileloader.controllers;

import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    @Value("${avatar.default}")
    public String defaultAvatarName;

    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Optional<String> uploadFile(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        return fileService.uploadFile(file.getInputStream(), userId + userAvatarNamePostfix);
    }

    @GetMapping("/")
    public List<String> getFilesList() {
        return fileService.getFilesList();
    }

    @GetMapping("/{fileName}")
    public Optional<String> getAvatarUrl(@PathVariable final String fileName) {
        return fileService.getAvatarUrl(fileName);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable final String fileName) {
        return ResponseEntity.ok(fileService.deleteFile(fileName));
    }

    @PostMapping(value = "/default-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Optional<String> uploadDefaultAvatar(@RequestPart("file") final MultipartFile file) throws IOException {
        return fileService.uploadFile(file.getInputStream(), defaultAvatarName);
    }

}
