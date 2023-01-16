package com.example.userservice.controllers;

import com.example.userservice.dto.UserDto;
import com.example.userservice.facades.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserFacade facade;
    @PostMapping
    @CrossOrigin
    public UserDto save(@RequestBody UserDto user) {
        return facade.save(user);
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable("userId") final Long userId) {
        return facade.get(userId);
    }
}
