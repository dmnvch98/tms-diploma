package com.example.apigateway.client.file;

import com.example.apigateway.dto.FileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files")
public interface FileClient {

    @PostMapping(value = "/{userId}")
    Optional<String> uploadFile(@RequestBody final FileDto fileDto, @PathVariable("userId") Long userId);

    @GetMapping("/{fileName}")
    Optional<String> getFileUrl(@PathVariable("fileName") String fileName);

    @DeleteMapping("/{fileName}")
    ResponseEntity<Boolean> deleteFile(@PathVariable("fileName") String fileName);

}
