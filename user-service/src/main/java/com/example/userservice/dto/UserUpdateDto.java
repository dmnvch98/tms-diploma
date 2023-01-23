package com.example.userservice.dto;

import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
public class UserUpdateDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Long nationality;
    String gender;
    String roles;
    Student student;
    Tutor tutor;
    List<LanguageLevelDto> languageLevels;
}
