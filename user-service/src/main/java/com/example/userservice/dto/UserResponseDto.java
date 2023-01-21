package com.example.userservice.dto;

import com.example.userservice.model.Country;
import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
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
