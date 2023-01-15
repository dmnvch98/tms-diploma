package com.example.apigateway.client;

import com.example.apigateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",
        url = "${services.user.url}/api/v1/users")
public interface UserClient {
    @PostMapping
    UserDto save(@RequestBody UserDto user);

    @GetMapping("/{userId}")
    UserDto get(@PathVariable("userId") final Long userId);
}
