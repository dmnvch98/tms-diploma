package com.example.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LanguageLevelDto {
    Long languageId;
    Long levelId;
}
