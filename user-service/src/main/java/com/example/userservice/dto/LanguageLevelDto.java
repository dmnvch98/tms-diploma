package com.example.userservice.dto;

import com.example.userservice.model.Language;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LanguageLevelDto {
    Language language;
    Long levelId;
}
