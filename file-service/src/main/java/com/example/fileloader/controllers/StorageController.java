package com.example.fileloader.controllers;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.fileloader.dto.StorageDto;
import com.example.fileloader.interfaces.FileService;
import com.example.fileloader.interfaces.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/v1/storages")
@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    private final FileService fileService;

    @ResponseStatus(CREATED)
    @PostMapping(value = "/files")
    public void uploadFile(@RequestPart("file") final MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        fileService.uploadFile(file.getOriginalFilename(), file.getInputStream(), metadata);
    }

    @GetMapping("/{storageName}/files")
    public ResponseEntity<List<String>> getFilesList(@PathVariable final String storageName) {
        return ResponseEntity.ok(fileService.getFilesList(storageName));
    }

    @GetMapping("/{storageName}/files/{fileName}")
    public void getFile(@PathVariable final String storageName, @PathVariable final String fileName,
                                     HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:4566/" + storageName + "/" + fileName);
    }

    @DeleteMapping("/{storageName}/files/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable final String storageName, @PathVariable final String fileName) {
        fileService.deleteFile(storageName, fileName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStorage(@RequestBody final StorageDto storage) {
        storageService.createStorage(storage.getStorageName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Bucket>> getStorages() {
        return ResponseEntity.ok(storageService.getStorage());
    }

    @DeleteMapping("/{storageName}")
    public ResponseEntity<?> deleteStorage(@PathVariable final String storageName) {
        storageService.deleteStorage(storageName);
        return ResponseEntity.ok().build();
    }
}
