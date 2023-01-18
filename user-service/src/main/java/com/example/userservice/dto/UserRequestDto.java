package com.example.userservice.dto;

import com.example.userservice.model.Country;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
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
