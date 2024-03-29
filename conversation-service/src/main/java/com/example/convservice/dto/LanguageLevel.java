package com.example.convservice.dto;

import com.example.convservice.model.Language;
import com.example.convservice.model.Level;
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
