package com.example.userservice.controllers;

import com.example.userservice.dto.CredentialsDto;
import com.example.userservice.dto.RefreshTokenSave;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.facades.UserFacade;
import com.example.userservice.model.User;
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

    @PutMapping("/{userId}")
    public UserResponseDto update(@RequestBody UserRequestDto userRequestDto, @PathVariable("userId") Long userId) {
        return facade.update(userRequestDto, userId);
    }

    @DeleteMapping("/languages/{languageId}/levels/{levelId}/users/{userId}")
    public UserResponseDto deleteUserLanguageLevel(
            @PathVariable("languageId") Long languageId,
            @PathVariable("levelId") Long levelId,
            @PathVariable("userId") Long userId) {
        return facade.deleteUserLanguageLevels(languageId, levelId, userId);
    }

    @GetMapping("/tutors/{tutorId}")
    @CrossOrigin
    public UserResponseDto findUserByTutorId(@PathVariable("tutorId") Long tutorId) {
        return facade.findUserByTutorId(tutorId);
    }

    @GetMapping("/students/{studentId}")
    @CrossOrigin
    public UserResponseDto findUserByStudentId(@PathVariable("studentId") Long studentId) {
        return facade.findUserByStudentId(studentId);
    }

    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable("email") String email) {
        return facade.findUserByEmail(email);
    }

    @PostMapping("/exists")
    public Boolean existsByEmailAndPassword(@RequestBody CredentialsDto credentialsDto) {
        return facade.existsByEmailAndPassword(credentialsDto);
    }

    @PatchMapping("/refresh-token")
    void updateRefreshToken(@RequestBody RefreshTokenSave refreshToken) {
        facade.updateRefreshToken(refreshToken.getToken(), refreshToken.getUserId());
    }

    @PatchMapping("/{userId}/avatar")
    int setAvatar(@PathVariable("userId") Long userId) {
        return facade.setAvatar(userId);
    }

    @DeleteMapping("/{userId}/avatar")
    int deleteAvatar(@PathVariable("userId") Long userId) {
        return facade.deleteAvatar(userId);
    }

}
