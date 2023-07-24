package com.example.apigateway.dto;

import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
@Jacksonized
public class UserRequestDto {
    String firstName;
    String lastName;
    String email;
    @NonFinal
    @Setter
    String password;
    Long nationality;
    String gender;
    List<String> roles;
    Student student;
    Tutor tutor;
    List<LanguageLevelDto> languageLevels;
    String location;
}
