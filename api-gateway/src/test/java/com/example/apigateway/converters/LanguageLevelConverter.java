package com.example.apigateway.converters;

import com.example.apigateway.dto.LanguageLevelDto;
import com.example.apigateway.model.LanguageLevel;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageLevelConverter {
    LanguageLevelDto languageLevelToDto(LanguageLevel languageLevel);
}
