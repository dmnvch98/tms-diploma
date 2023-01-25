package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.User;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;
    private final UserConverter userConverter;
    private final LanguageLevelConverter languageLevelConverter;

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto, Long userId) {
        User user = userConverter.userRequestDtoToUser(userRequestDto, userId);
        user = userService.save(user);
        List<LanguageLevelDto> languageLevels =
                extractUserLanguageLevelsFromDto(userRequestDto, user.getId())
                        .stream()
                        .map(languageLevelService::saveUserLanguageLevel)
                        .map(languageLevelService::userLanguageLevelToLl)
                        .map(languageLevelConverter::languageLevelToDto)
                        .toList();
        return userConverter.userToResponseDto(user, languageLevels);
    }

    public UserResponseDto get(Long id) {
        return userConverter.userToResponseDto(userService.get(id), findLanguageLevelsByUserId(id));
    }

    private List<LanguageLevelDto> findLanguageLevelsByUserId(Long userId) {
        return languageLevelService
                .findLanguageLevelsByUserId(userId)
                .stream()
                .map(languageLevelConverter::languageLevelToDto)
                .toList();
    }

    public Boolean isEmailExists(String email) {
        return userService.isEmailExists(email);
    }


    private List<UserLanguageLevel> extractUserLanguageLevelsFromDto(UserRequestDto userRequestDto, Long userId) {
        return userRequestDto.getLanguageLevels().stream()
                .map(x -> languageLevelService
                        .getLanguageLevelId(x.getLevel().getLevelId(), x.getLanguage().getLanguageId()))
                .map(x -> languageLevelConverter.languageLevelIdToUll(x, userId))
                .toList();
    }

    public UserResponseDto deleteUserLanguageLevels(Long languageId, Long levelId, Long userId) {
        Long languageLevelId = languageLevelService.getLanguageLevelId(levelId, languageId);
        languageLevelService.deleteUserLanguageLevel(languageLevelId, userId);
        return get(userId);
    }
}
