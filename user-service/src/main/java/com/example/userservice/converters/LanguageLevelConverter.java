package com.example.userservice.converters;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.UserLanguageLevel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LanguageLevelConverter {
    @Mapping(target = "languageId", expression = "java(languageLevel.getLanguage().getLanguageId())")
    @Mapping(target = "levelId", expression = "java(languageLevel.getLevel().getLevelId())")
    LanguageLevelDto languageLevelToDto(LanguageLevel languageLevel);

    UserLanguageLevel languageLevelIdToUll(Long languageLevelId, Long userId);
}
