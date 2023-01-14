package com.example.userservice.controllers;

import com.example.dto.UserSignUpDto;
import com.example.userservice.model.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    @PostMapping
    public ResponseEntity<UserSignUpDto> save(@RequestBody UserSignUpDto user) {
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(service.get(userId));
    }
}
