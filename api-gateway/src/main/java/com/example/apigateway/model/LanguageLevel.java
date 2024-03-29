package com.example.apigateway.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
@Value
@Builder
@Jacksonized
public class LanguageLevel {
    Long languageLevelId;
    Language language;
    Level level;
}
