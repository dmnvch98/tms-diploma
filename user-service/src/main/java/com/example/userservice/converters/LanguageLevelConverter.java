package com.example.userservice.converters;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.UserLanguageLevel;
import org.mapstruct.Mapper;

@Mapper
public interface LanguageLevelConverter {
    LanguageLevelDto languageLevelToDto(LanguageLevel languageLevel);
    UserLanguageLevel languageLevelIdToUll(Long languageLevelId, Long userId);
}
