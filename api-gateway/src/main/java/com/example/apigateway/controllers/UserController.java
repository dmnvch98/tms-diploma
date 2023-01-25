package com.example.apigateway.controllers;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> get(@PathVariable("userId") final Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @CrossOrigin
    @GetMapping("/is-exists/{email}")
    public ResponseEntity<Boolean> isEmailExists(@PathVariable("email")String email) {
        return ResponseEntity.ok(userService.isEmailExists(email));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto userDto,
                                                  @PathVariable Long userId) {
        return ResponseEntity.ok(userService.update(userDto, userId));
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
