package com.example.apigateway.dto;

import com.example.apigateway.model.Country;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
public class UserRequestDto {
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
