package com.example.userservice.controllers;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.facades.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserFacade facade;
    @PostMapping
    public UserResponseDto save(@RequestBody UserRequestDto user) {
        return facade.save(user);
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable("userId") final Long userId) {
        return facade.get(userId);
    }

    @GetMapping("/is-exists/{email}")
    public Boolean isEmailExists(@PathVariable("email") final String email) {
        return facade.isEmailExists(email);
    }

    @PutMapping
    public UserResponseDto update(@RequestBody UserRequestDto user) {
        return facade.updateUser(user);
    }
}
