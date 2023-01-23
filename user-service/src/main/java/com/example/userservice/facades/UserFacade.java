package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.dto.UserUpdateDto;
import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.model.Tutor;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import com.example.userservice.services.StudentService;
import com.example.userservice.services.TutorService;
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
    private final TutorService tutorService;
    private final StudentService studentService;

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userService.save(userConverter.userRequestDtoToUser(userRequestDto));
        List<LanguageLevelDto> languageLevels =
                extractUserLanguageLevelsFromDto(userRequestDto, user.getId())
                        .stream()
                        .map(languageLevelService::saveUserLanguageLevel)
                        .map(languageLevelService::userLanguageLevelToLl)
                        .map(languageLevelConverter::languageLevelToDto)
                        .toList();
        UserResponseDto userResponseDto = userConverter.userToRespDto(user, languageLevels);
        return createUserRoleEntity(userRequestDto.getRoles(), userResponseDto);

    }

    public UserResponseDto get(Long id) {
        return userConverter.userToRespDto(userService.get(id), findLanguageLevelsByUserId(id));
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

    private UserResponseDto createUserRoleEntity(String roles, UserResponseDto userResponseDto) {
        if (roles.equals("Student")) {
            userResponseDto.setStudent(studentService.saveStudent(
                    Student
                            .builder()
                            .userId(userResponseDto.getId())
                            .build()));
        } else {
            userResponseDto.setTutor(tutorService.saveTutor(
                    Tutor
                            .builder()
                            .userId(userResponseDto.getId())
                            .build()));
        }
        return userResponseDto;
    }

    public UserResponseDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userConverter.userUpdateDtoToUser(userUpdateDto);
        user = userService.save(user);
        return userConverter.userToRespDto(user, findLanguageLevelsByUserId(user.getId()));
//        User user = userConverter.userRequestDtoToUser(userRequestDto);
//        userService.updateUser(user);
//        UserResponseDto userResponseDto = userConverter.userToResponseDto(userService.get(userRequestDto.getId()));
//
//        if (userRequestDto.getLanguageLevels() != null) {
//            saveUserLanguageLevels(extractUserLanguageLevelsFromDto(userRequestDto, userRequestDto.getId()));
//        }
//
//        userResponseDto.toBuilder().languageLevels(findLanguageLevelsByUserId(userRequestDto.getId()));
//
//        return userResponseDto;
    }

//    private List<UserLanguageLevel> saveUserLanguageLevels(List<UserLanguageLevel> userLanguageLevels) {
//        return userLanguageLevels
//                .stream()
//                .map(languageLevelService::saveUserLanguageLevel)
//                .toList();
//    }

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
