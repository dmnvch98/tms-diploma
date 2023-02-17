package com.example.apigateway.client.file;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files")
public interface FileClient {

    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Optional<String> uploadFile(@RequestPart("file") final MultipartFile file, @PathVariable("userId") Long userId);

    @GetMapping("/")
    ResponseEntity<List<String>> getFilesList();


    @GetMapping("/{fileName}")
    String getFileUrl(@PathVariable("fileName") String fileName);

    @DeleteMapping("/{fileName}")
    ResponseEntity<Boolean> deleteFile(@PathVariable("fileName") String fileName);

}
