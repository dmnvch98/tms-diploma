package com.example.apigateway.dto;

import com.example.apigateway.model.Country;
import com.example.apigateway.model.LanguageLevel;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder(toBuilder = true)
@Value
@Jacksonized
public class UserResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Country nationality;
    String gender;
    List<String> roles;
    @NonFinal
    @Setter
    Student student;
    @NonFinal
    @Setter
    Tutor tutor;
    List<LanguageLevel> languageLevels;
    String location;
    @NonFinal
    @Setter
    String avatarName;
}
