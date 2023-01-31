package com.example.apigateway.dto;

import com.example.apigateway.model.Language;
import com.example.apigateway.model.Level;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class LanguageLevelDto {
    Language language;
    Level level;
}
