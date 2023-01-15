package com.example.userservice.dto;

import com.example.userservice.model.LanguageLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
@Jacksonized
public class UserDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String nationality;

    String gender;
    String roles;
    List<LanguageLevelDto> languageLevels;
}
