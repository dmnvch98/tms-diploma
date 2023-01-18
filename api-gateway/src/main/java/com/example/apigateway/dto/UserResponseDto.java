package com.example.apigateway.dto;

import com.example.apigateway.model.Country;

import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
@Jacksonized
public class UserResponseDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Country nationality;
    String gender;
    Student student;
    Tutor tutor;
    List<LanguageLevelDto> languageLevels;
}
