package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> get(Authentication authentication) {
        return ResponseEntity.ok(userService.get(((PrincipalUser) authentication.getPrincipal()).getUserId()));
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> isEmailExists(@PathVariable("email")String email) {
        return ResponseEntity.ok(userService.isEmailExists(email));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto userDto,
                                                  Authentication authentication) {
        return ResponseEntity.ok(userService.update(userDto,
                        ((PrincipalUser) authentication.getPrincipal()).getUserId()));
    }

    @DeleteMapping("/languages/{languageId}/levels/{levelId}/users/{userId}")
    public UserResponseDto deleteUserLanguageLevel(
            @PathVariable("languageId") Long languageId,
            @PathVariable("levelId") Long levelId,
            @PathVariable("userId") Long userId) {
        return userService.deleteUserLanguageLevel(languageId, levelId, userId);
    }

    @GetMapping("/tutors/{tutorId}")
    public UserResponseDto findUserByTutorId(@PathVariable("tutorId") Long tutorId) {
        return userService.findUserByTutorId(tutorId);
    }

    @GetMapping("/students/{studentId}")
    public UserResponseDto findUserByStudentId(@PathVariable("studentId") Long studentId) {
        return userService.findUserByStudentId(studentId);
    }
}
