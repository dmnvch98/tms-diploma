package com.example.apigateway.controllers;

import com.example.apigateway.dto.UserDto;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> get(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }
}
