package com.example.conversationservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
public class TutorCardInfo {
    Long userId;
    String firstName;
    String lastName;
    List<LanguageLevelDto> languageLevels;

}
