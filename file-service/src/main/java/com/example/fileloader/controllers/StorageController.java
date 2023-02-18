package com.example.fileloader.controllers;

import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
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
public class StorageController {

    private final FileService fileService;

    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Optional<String> uploadFile(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        return fileService.uploadFile(file.getInputStream(), userId + "_avatar.png");
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getFilesList() {
        return ResponseEntity.ok(fileService.getFilesList());
    }

    @GetMapping("/{fileName}")
    public Optional<String> getFileUrl(@PathVariable final String fileName) {
        return fileService.getAvatarUrl(fileName);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable final String fileName) {
        return ResponseEntity.ok(fileService.deleteFile(fileName));
    }

    @PostMapping(value = "/default-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Optional<String> uploadDefaultAvatar(@RequestPart("file") final MultipartFile file) throws IOException {
        return fileService.uploadFile(file.getInputStream(), "default_avatar.png");
    }

}
