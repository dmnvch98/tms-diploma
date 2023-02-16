package com.example.apigateway.client.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files")
public interface FileClient {

    @PostMapping(value = "/{userId}", consumes = MediaType.MULTIPART_MIXED_VALUE)
    void uploadFile(@RequestPart("file") final MultipartFile file, @PathVariable("userId") Long userId);

    @GetMapping("/")
    ResponseEntity<List<String>> getFilesList();


    @GetMapping("/{fileName}")
    String getFileUrl(@PathVariable("fileName") String fileName);

    @DeleteMapping("/{fileName}")
    ResponseEntity<?> deleteFile(@PathVariable("fileName") String fileName);

}
