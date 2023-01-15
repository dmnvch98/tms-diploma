package com.example.userservice.converters;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {
    User dtoToUser(UserDto dto);

    UserDto userToDto(User user);
}
