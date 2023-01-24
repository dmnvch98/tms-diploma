package com.example.userservice.converters;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {CountryFlag.class})
public interface UserConverter {

//    @Mapping(target = "nationality", source = "nationality")
    User userRequestDtoToUser(UserRequestDto userDto, Long id);

    UserResponseDto userToRespDto(User user, List<LanguageLevelDto> languageLevels);
}
