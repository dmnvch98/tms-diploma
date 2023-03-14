package com.example.userservice.dto;

import com.example.userservice.model.LanguageLevel;
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
    List<LanguageLevel> languageLevels;
    String avatarName;
}
