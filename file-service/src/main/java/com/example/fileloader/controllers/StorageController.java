package com.example.fileloader.controllers;

import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class StorageController {

    private final FileService fileService;

    @PostMapping(value = "/{userId}")
    public void uploadFile(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        fileService.uploadFile(file.getInputStream(), userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> getFilesList() {
        return ResponseEntity.ok(fileService.getFilesList());
    }

    @GetMapping("/{fileName}")
    public String getFileUrl(@PathVariable final String fileName) {
        return fileService.getFileUrl(fileName);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable final String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }

}
