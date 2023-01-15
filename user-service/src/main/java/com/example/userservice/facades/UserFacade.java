package com.example.userservice.facades;

import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import com.example.userservice.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final LanguageLevelService languageLevelService;

    private final UserConverter userConverter;

    public ResponseEntity<User> save(UserDto dto) {
        User user = userService.save(userConverter.dtoToUser(dto));
//        List<Long> userLanguageLevels = Arrays.stream(dto.getLanguageLevels())
//                .map(x -> languageLevelService.getLanguageLevelId(x.getLevelId(), x.getLanguageId()))
//                .toList();

        Arrays.stream(dto.getLanguageLevels())
                .map(x -> languageLevelService
                        .getLanguageLevelId(x.getLevelId(), x.getLanguageId()))
                .map(x -> UserLanguageLevel
                        .builder()
                        .languageLevelId(x)
                        .user_id(user.getId())
                        .build())
                .forEach(languageLevelService::saveUserLanguageLevel);
        return ResponseEntity.ok(user);
    }
}
