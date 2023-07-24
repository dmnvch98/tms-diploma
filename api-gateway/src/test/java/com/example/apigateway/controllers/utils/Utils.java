package com.example.apigateway.controllers.utils;

import com.example.apigateway.dto.LanguageLevelDto;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.Language;
import com.example.apigateway.model.Level;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public final class Utils {
    private Utils() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static UserRequestDto createUserRequestDto() {
        List<LanguageLevelDto> languageLevelDtos = new ArrayList<>();
        languageLevelDtos.add(createLanguageLevelDto(7L, "English", 2L, "Elementary"));
        languageLevelDtos.add(createLanguageLevelDto(9L, "Finnish", 5L, "Advanced"));

        return UserRequestDto.builder()
            .firstName("Eugen")
            .lastName("Dzemyanovich")
            .email(generateEmail())
            .password("12345")
            .nationality(1L)
            .gender("male")
            .roles(List.of("Tutor"))
            .location("Minsk")
            .languageLevels(languageLevelDtos)
            .student(generateStudent("init about me", "init url"))
            .tutor(generateTutor("init about me", "init url"))
            .build();
    }

    public static LanguageLevelDto createLanguageLevelDto(long langId, String langDesc, long levelId, String levelDesc) {
        Language language = Language.builder()
            .languageId(langId)
            .description(langDesc)
            .build();
        Level level = Level.builder()
            .levelId(levelId)
            .description(levelDesc)
            .build();
        return LanguageLevelDto.builder()
            .language(language)
            .level(level)
            .build();
    }

    private static Student generateStudent(String aboutMe, String presentationFileName) {
        return Student.builder()
            .aboutMe(aboutMe)
            .presentationFileName(presentationFileName)
            .build();
    }

    private static Tutor generateTutor(String aboutMe, String presentationFileName) {
        return Tutor.builder()
            .aboutMe(aboutMe)
            .presentationUrl(presentationFileName)
            .build();
    }

    private static String generateEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = dateFormat.format(new Date());

        String uniquePart = "example.com";

        return "test_" + currentTime + "@" + uniquePart;
    }

    public static UserResponseDto parseUserRespDtoFromJson(String response) throws JsonProcessingException {
        return objectMapper.readValue(response, UserResponseDto.class);
    }
}
