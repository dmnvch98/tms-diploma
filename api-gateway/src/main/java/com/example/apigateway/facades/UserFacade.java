package com.example.apigateway.facades;

import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;

    public UserResponseDto save(final UserRequestDto userDto) {
        return userService.save(userDto);
    }

    public UserResponseDto get(final Long userId) {
        return userService.get(userId);
    }

    public Boolean isEmailExists(final String email) {
        return userService.isEmailExists(email);
    }

    public UserResponseDto update(final UserRequestDto userDto, final Long userId) {
        return userService.update(userDto, userId);
    }

    public UserResponseDto findUserByTutorId(final Long tutorId) {
        return userService.findUserByTutorId(tutorId);
    }

    public UserResponseDto findUserByStudentId(final Long studentId) {
        return userService.findUserByStudentId(studentId);
    }
}
