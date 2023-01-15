package com.example.userservice.controllers;

import com.example.userservice.dto.UserDto;
import com.example.userservice.facades.UserFacade;
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
    private final UserFacade facade;
    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        return ResponseEntity.ok(facade.save(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(service.get(userId));
    }
}
