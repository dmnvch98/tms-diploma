package com.example.apigateway.services;

import com.example.apigateway.client.user.UserClient;
import com.example.apigateway.config.security.PasswordConfig;
import com.example.apigateway.dto.*;
import com.example.apigateway.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserClient userClient;
    private final PasswordConfig passwordConfig;

    public UserResponseDto save(UserRequestDto userDto) {
        userDto.setPassword(passwordConfig.passwordEncoder().encode(userDto.getPassword()));
        return userClient.save(userDto);
    }

    public UserResponseDto get(Long userId) {
        try {
            return userClient.get(userId);
        } catch (Exception e) {
            log.error("An error occurred during fetching current user info {}", e.getMessage());
        }
        return null;
    }

    public Boolean isEmailExists(String email) {
        return userClient.isEmailExists(email);
    }

    public UserResponseDto update(UserRequestDto userRequestDto, Long userId) {
        return userClient.update(userRequestDto, userId);
    }

    public User findUserByEmail(String email) {
        return userClient.findUserByEmail(email);
    }

    public Boolean verifyUser(CredentialsDto credentialsDto) {
        User user = findUserByEmail(credentialsDto.getEmail());
        return user != null && passwordConfig
            .passwordEncoder()
            .matches(credentialsDto.getPassword(), user.getPassword());
    }

    public UserResponseDto findUserByTutorId(Long tutorId) {
        return userClient.findUserByTutorId(tutorId);
    }

    public UserResponseDto findUserByStudentId(Long studentId) {
        return userClient.findUserByStudentId(studentId);
    }

    public void saveRefreshToken(UserRefreshToken refreshToken) {
        userClient.updateRefreshToken(refreshToken);
    }
}
