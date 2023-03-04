package com.example.convservice.client;

import com.example.convservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files/")
public interface FileClient {
    @GetMapping("/{fileName}")
    ResponseDto getAvatarUrl(@PathVariable("fileName") final String fileName);
}
