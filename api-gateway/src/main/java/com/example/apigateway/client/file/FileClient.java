package com.example.apigateway.client.file;

import com.example.apigateway.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "file-service",
        url = "${services.file.url}/api/v1/storages")
public interface FileClient {
    @PostMapping(value = "/{storageName}/files")
    void uploadFile(@RequestPart("file") final MultipartFile file,
                           @PathVariable("storageName") final String storageName);

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createStorage(@RequestBody final StorageDto storage);
}
