package com.example.apigateway.client;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",
        url = "${services.user.url}/api/v1/users")
public interface UserClient {
    @PostMapping
    UserResponseDto save(@RequestBody UserRequestDto user);

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable("userId") final Long userId);

    @GetMapping("/is-exists/{email}")
    Boolean isEmailExists(@PathVariable("email") final String email);
}
