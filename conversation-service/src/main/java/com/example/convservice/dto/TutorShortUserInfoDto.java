package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
public class TutorShortUserInfoDto {
    Long userId;
    Long tutorId;
    String firstName;
    String lastName;
    List<LanguageLevelDto> languageLevels;
    String avatarName;
}
