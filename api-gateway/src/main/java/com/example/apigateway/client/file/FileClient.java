package com.example.apigateway.client.file;

import com.example.apigateway.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files")
public interface FileClient {

    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto uploadAvatar(@RequestPart("file") final MultipartFile file, @PathVariable("userId") Long userId);

    @GetMapping("avatar/{fileName}")
    ResponseDto getAvatarUrl(@PathVariable("fileName") String fileName);

    @DeleteMapping("avatar/{fileName}")
    ResponseEntity<Boolean> deleteAvatar(@PathVariable("fileName") String fileName);

}
