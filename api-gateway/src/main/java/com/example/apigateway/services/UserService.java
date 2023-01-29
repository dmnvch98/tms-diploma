package com.example.apigateway.services;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.UserRefreshToken;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserResponseDto save(UserRequestDto userDto) {
        return userClient.save(userDto);
    }

    public UserResponseDto get(Long userId) {
        return userClient.get(userId);
    }

    public Boolean isEmailExists(String email) {
        return userClient.isEmailExists(email);
    }

    public UserResponseDto update(UserRequestDto userRequestDto, Long userId) {
        return userClient.update(userRequestDto, userId);
    }

    public UserResponseDto deleteUserLanguageLevel(Long languageId, Long levelId, Long userId) {
        return userClient.deleteUserLanguageLevel(languageId, levelId, userId);
    }

    public User findUserByEmail(String email) {
        return userClient.findUserByEmail(email);
    }

    public Boolean isUserExists(CredentialsDto credentialsDto) {
        return userClient.existsByEmailAndPassword(credentialsDto);
    }

    public Boolean verifyUser(CredentialsDto credentialsDto) {
        return userClient.existsByEmailAndPassword(credentialsDto);
    }

    public UserResponseDto findUserByTutorId (Long tutorId) {
        return userClient.findUserByTutorId(tutorId);
    }

    public UserResponseDto findUserByStudentId (Long studentId) {
        return userClient.findUserByStudentId(studentId);
    }

    public void saveRefreshToken(UserRefreshToken refreshToken) {
        userClient.updateRefreshToken(refreshToken);
    }
}
