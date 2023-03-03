package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.CredentialsDto;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;
    private final UserConverter userConverter;
    private final LanguageLevelConverter languageLevelConverter;


    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userConverter.userRequestDtoToUserSave(userRequestDto);
        user = userService.save(user);
        List<UserLanguageLevel> userLanguageLevels =
            extractUserLanguageLevelsFromDto(userRequestDto, user.getId());
        List<LanguageLevelDto> languageLevelDtoList = saveUserLanguageLevels(userLanguageLevels);
        return userConverter.userToResponseDto(user, languageLevelDtoList);
    }

    @Transactional
    public UserResponseDto update(UserRequestDto userRequestDto, Long userId) {
        User user = userConverter.userRequestDtoToUserUpdate(userRequestDto, userId);
        user = userService.save(user);
        List<UserLanguageLevel> existingUserLanguageLevels = languageLevelService.findAllUllByUserId(userId);

        List<UserLanguageLevel> userLanguageLevels =
            extractUserLanguageLevelsFromDto(userRequestDto, user.getId());

        List<Long> languageLevelsIdToDelete = findLanguageLevelIdsToDelete(existingUserLanguageLevels, userLanguageLevels);
        if (languageLevelsIdToDelete.size() > 0) {
            languageLevelsIdToDelete.forEach(x -> languageLevelService.deleteUserLanguageLevel(x, userId));
        }
        List<LanguageLevelDto> languageLevelDtoList = saveUserLanguageLevels(userLanguageLevels);
        return userConverter.userToResponseDto(user, languageLevelDtoList);
    }

    public UserResponseDto get(Long id) {
        return userConverter.userToResponseDto(userService.get(id), findLanguageLevelsByUserId(id));
    }

    public List<LanguageLevelDto> findLanguageLevelsByUserId(Long userId) {
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

    public UserResponseDto findUserByTutorId(Long tutorId) {
        User user = userService.findUserByTutorId(tutorId);
        return userConverter.userToResponseDto(user, findLanguageLevelsByUserId(user.getId()));
    }

    public UserResponseDto findUserByStudentId(Long studentId) {
        User user = userService.findUserByStudentId(studentId);
        return userConverter.userToResponseDto(user, findLanguageLevelsByUserId(user.getId()));
    }

    public User findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    public Boolean existsByEmailAndPassword(CredentialsDto credentialsDto) {
        return userService.existsByEmailAndPassword(credentialsDto.getEmail(), credentialsDto.getPassword());
    }

    public void updateRefreshToken(String token, Long userId) {
        userService.updateRefreshToken(token, userId);
    }

    public List<LanguageLevelDto> saveUserLanguageLevels(List<UserLanguageLevel> userLanguageLevels) {
        return userLanguageLevels
            .stream()
            .map(languageLevelService::saveUserLanguageLevel)
            .map(languageLevelService::userLanguageLevelToLl)
            .map(languageLevelConverter::languageLevelToDto)
            .toList();
    }

    public int setAvatar(Long userId) {
        return userService.setAvatar(userId);
    }

    public int deleteAvatar(Long userId) {
        return userService.deleteAvatar(userId);
    }

    private List<Long> findLanguageLevelIdsToDelete(List<UserLanguageLevel> existing,
                                                    List<UserLanguageLevel> fromDto) {
        List<Long> list1 = new ArrayList<>(existing.stream().map(UserLanguageLevel::getLanguageLevelId).toList());
        List<Long> list2 = fromDto.stream().map(UserLanguageLevel::getLanguageLevelId).toList();
        list1.removeAll(list2);
        return list1;
    }
}
