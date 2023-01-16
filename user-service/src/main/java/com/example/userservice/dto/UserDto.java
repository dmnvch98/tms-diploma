package com.example.userservice.dto;

import com.example.userservice.model.Country;
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
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    Country nationality;
    String gender;
    String roles;
    List<LanguageLevelDto> languageLevels;
}
