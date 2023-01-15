package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
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
        User user = userService.save(userConverter.dtoToUser(dto));
        List<UserLanguageLevel> userLanguageLevelsIds = dto.getLanguageLevels().stream()
                .map(x -> languageLevelService
                        .getLanguageLevelId(x.getLevelId(), x.getLanguageId()))
                .map(x -> UserLanguageLevel
                        .builder()
                        .languageLevelId(x)
                        .user_id(user.getId())
                        .build())
                .toList();

        userLanguageLevelsIds.forEach(languageLevelService::saveUserLanguageLevel);

        List<LanguageLevelDto> userLanguageLevels =
                userLanguageLevelsIds.stream()
                        .map(x -> languageLevelService.getLanguageLevelById(x.getLanguageLevelId()))
                        .map(languageLevelConverter::languageLevelToDto)
                        .toList();
        UserDto userToReturn = userConverter.userToDto(user);
        userToReturn.setLanguageLevels(userLanguageLevels);
        return userToReturn;
    }
}
