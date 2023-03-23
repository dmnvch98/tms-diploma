package com.example.userservice.dto;

import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
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
