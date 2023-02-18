package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.facades.UserFacade;
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

    public final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userDto) {
        return ResponseEntity.ok(userFacade.save(userDto));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> get(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(userFacade.get(userId));
    }

    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> isEmailExists(@PathVariable("email") String email) {
        return ResponseEntity.ok(userFacade.isEmailExists(email));
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto userDto,
                                                  Authentication authentication) {

        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(userFacade.update(userDto, userId));
    }

    @GetMapping("/tutors/{tutorId}")
    public UserResponseDto findUserByTutorId(@PathVariable("tutorId") Long tutorId) {
        return userFacade.findUserByTutorId(tutorId);
    }

    @GetMapping("/students/{studentId}")
    public UserResponseDto findUserByStudentId(@PathVariable("studentId") Long studentId) {
        return userFacade.findUserByStudentId(studentId);
    }
}
