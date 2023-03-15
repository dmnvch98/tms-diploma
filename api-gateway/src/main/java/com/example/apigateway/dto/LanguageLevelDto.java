package com.example.apigateway.dto;

import com.example.apigateway.model.Language;
import com.example.apigateway.model.Level;
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
