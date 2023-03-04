package com.example.userservice.converters;

import com.example.userservice.converters.utils.CountryFlag;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.TutorCardInfo;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {CountryFlag.class})
public interface UserConverter {
    User userRequestDtoToUserSave(UserRequestDto userDto);

    User userRequestDtoToUserUpdate(UserRequestDto userDto, Long id);

    UserResponseDto userToResponseDto(User user, List<LanguageLevelDto> languageLevels);

    @Mapping(target = "tutorId", expression = "java(user.getTutor().getTutorId())")
    @Mapping(target = "userId", source = "user.id")
    TutorCardInfo userToTutorCardInfo(User user, List<LanguageLevelDto> languageLevels);

}
