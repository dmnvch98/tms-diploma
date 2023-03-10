package com.example.apigateway.client.user;

import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.UserRefreshToken;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service",
        url = "${services.user.url}/api/v1/users")
public interface UserClient {
    @PostMapping
    UserResponseDto save(@RequestBody UserRequestDto user);

    @GetMapping("/{userId}")
    UserResponseDto get(@PathVariable("userId") final Long userId);

    @GetMapping("/is-exists/{email}")
    Boolean isEmailExists(@PathVariable("email") final String email);

    @PutMapping("/{userId}")
    UserResponseDto update(@RequestBody UserRequestDto userRequestDto, @PathVariable("userId") Long userId);

    @GetMapping("/email/{email}")
    User findUserByEmail(@PathVariable("email") String email);

    @PostMapping("/exists")
    Boolean existsByEmailAndPassword(@RequestBody CredentialsDto credentialsDto);

    @GetMapping("/students/{studentId}")
    UserResponseDto findUserByStudentId(@PathVariable("studentId") Long studentId);

    @GetMapping("/tutors/{tutorId}")
    UserResponseDto findUserByTutorId(@PathVariable("tutorId") Long tutorId);

    @PatchMapping("/refresh-token")
    void updateRefreshToken(UserRefreshToken refreshToken);

    @PatchMapping("/{userId}/avatar")
    int setAvatar(@PathVariable("userId") Long userId);

    @DeleteMapping("/{userId}/avatar")
    int deleteAvatar(@PathVariable("userId") Long userId);
}
