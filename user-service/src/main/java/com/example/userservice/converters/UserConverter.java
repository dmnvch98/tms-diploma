package com.example.userservice.converters;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import org.mapstruct.*;

@Mapper(uses = {CountryFlag.class})
public interface UserConverter {

    @Mapping(target = "nationality", source = "nationality")
    User userRequestDtoToUser(UserRequestDto userDto);

    UserResponseDto userToResponseDto(User user);
}
