package com.example.userservice.dto;

import com.example.userservice.model.Language;
import com.example.userservice.model.Level;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LanguageLevelDto {
    Language language;
    Level level;
}
