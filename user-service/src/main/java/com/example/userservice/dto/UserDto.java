package com.example.userservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class UserDto {
    String firstName;
    String lastName;
    String email;
    String password;
    String nationality;

    String gender;
    String roles;
    LanguageLevelDto[] languageLevels;
}
