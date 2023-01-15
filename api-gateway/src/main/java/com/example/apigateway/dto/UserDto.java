package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Data;
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
    String nationality;

    String gender;
    String roles;
    List<LanguageLevelDto> languageLevels;
}
