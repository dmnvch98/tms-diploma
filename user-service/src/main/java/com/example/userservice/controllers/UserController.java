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
        return facade.save(user, null);
    }

    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable("userId") final Long userId) {
        return facade.get(userId);
    }

    @GetMapping("/is-exists/{email}")
    public Boolean isEmailExists(@PathVariable("email") final String email) {
        return facade.isEmailExists(email);
    }

    @PutMapping("/{userId}")
    public UserResponseDto update(@RequestBody UserRequestDto userRequestDto, @PathVariable("userId") Long userId) {
        return facade.save(userRequestDto, userId);
    }

    @DeleteMapping("/languages/{languageId}/levels/{levelId}/users/{userId}")
    public UserResponseDto deleteUserLanguageLevel(
            @PathVariable("languageId") Long languageId,
            @PathVariable("levelId") Long levelId,
            @PathVariable("userId") Long userId) {
        return facade.deleteUserLanguageLevels(languageId, levelId, userId);
    }
}
