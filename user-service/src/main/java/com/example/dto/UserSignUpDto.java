package com.example.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class UserSignUpDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String nationality;
    String roles;
    LanguageLevelDto[] languageLevels;
}
