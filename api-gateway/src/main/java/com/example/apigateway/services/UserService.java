package com.example.apigateway.services;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
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
}
