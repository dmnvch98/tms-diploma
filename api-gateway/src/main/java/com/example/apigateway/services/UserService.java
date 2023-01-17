package com.example.apigateway.services;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public UserDto save(UserDto userDto) {
        return userClient.save(userDto);
    }

    public UserDto get(Long userId) {
        return userClient.get(userId);
    }

    public Boolean isEmailExists(String email) {
        return userClient.isEmailExists(email);
    }
}
