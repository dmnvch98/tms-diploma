package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import com.example.userservice.services.LanguageLevelService;
import com.example.userservice.services.RoleService;
import com.example.userservice.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;
    private final UserConverter userConverter;
    private final LanguageLevelConverter languageLevelConverter;
    private final RoleService roleService;

    @Transactional
    public UserResponseDto save(UserRequestDto dto) {
        UserResponseDto userDto = userConverter.userToResponseDto(userService.save(userConverter.dtoToUser(dto)));
        userDto.setLanguageLevels(dto.getLanguageLevels().stream()
                .map(x -> languageLevelService
                        .getLanguageLevelId(x.getLevel().getLevelId(), x.getLanguage().getLanguageId()))
                .map(x -> languageLevelConverter.languageLevelIdToUll(x, userDto.getId()))
                .map(languageLevelService::saveUserLanguageLevel)
                .map(languageLevelService::userLanguageLevelToLl)
                .map(languageLevelConverter::languageLevelToDto)
                .toList());
        if (dto.getRoles().equals("Student")) {
            userDto.setStudent(roleService.saveStudent(
                    Student
                            .builder()
                            .userId(userDto.getId())
                            .build()));
        } else {
            userDto.setTutor(roleService.saveTutor(
                    Tutor
                            .builder()
                            .userId(userDto.getId())
                            .build()));
        }
        return userDto;
    }

    public UserResponseDto get(Long id) {
        UserResponseDto userToReturn = userConverter.userToResponseDto(userService.get(id));
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

    public Boolean isEmailExists(String email) {
        return userService.isEmailExists(email);
    }
}
