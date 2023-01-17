package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import com.example.userservice.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;
    private final UserConverter userConverter;
    private final LanguageLevelConverter languageLevelConverter;

    public UserDto save(UserDto dto) {
        UserDto userDto = userConverter.userToDto(userService.save(userConverter.dtoToUser(dto)));
        dto.getLanguageLevels().stream()
                .map(x -> languageLevelService
                        .getLanguageLevelId(x.getLevel().getLevelId(), x.getLanguage().getLanguageId()))
                .map(x -> UserLanguageLevel
                        .builder()
                        .languageLevelId(x)
                        .user_id(userDto.getId())
                        .build())
                .forEach(languageLevelService::saveUserLanguageLevel);
        userDto.setLanguageLevels(getUserLanguageLevels(userDto.getId()));
        return userDto;
    }

    public UserDto get(Long id) {
        UserDto userToReturn = userConverter.userToDto(userService.get(id));
        userToReturn.setLanguageLevels(getUserLanguageLevels(id));
        return userToReturn;
    }

    private List<LanguageLevelDto> getUserLanguageLevels(Long userId) {
        return languageLevelService
                .findLanguageLevelsByUserId(userId)
                .stream()
                .map(languageLevelConverter::languageLevelToDto)
                .toList();
    }
}
