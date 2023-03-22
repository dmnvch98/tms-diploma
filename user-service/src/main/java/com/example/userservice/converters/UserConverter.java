package com.example.userservice.converters;

import com.example.userservice.converters.utils.CountryFlag;
import com.example.userservice.dto.*;
import com.example.userservice.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {CountryFlag.class})
public interface UserConverter {
    User userRequestDtoToUserSave(UserRequestDto userDto);

    User userRequestDtoToUserUpdate(UserRequestDto userDto, Long id);

    UserResponseDto userToResponseDto(User user,
                                      List<LanguageLevelDto> languageLevels,
                                      Integer tutorConversationCount,
                                      Integer studentConversationCount,
                                      Double tutorAverageRate,
                                      Double studentAverageRate
    );

    @Mapping(target = "tutorId", expression = "java(user.getTutor().getTutorId())")
    @Mapping(target = "userId", source = "user.id")
    TutorShortUserInfoDto userToTutorCardInfo(User user, List<LanguageLevelDto> languageLevels,  Double tutorAverageRate);

}
