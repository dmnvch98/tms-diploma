package com.example.conversationservice.dto;

import com.example.conversationservice.model.Language;
import com.example.conversationservice.model.Level;
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
