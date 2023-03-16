package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.Language;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.Level;
import com.example.userservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageLevelFacade {
    private final LanguageLevelService languageLevelService;
    private final LanguageLevelConverter languageLevelConverter;

    public LanguageLevelDto languageLevelIdToLanguageLevelDto(Long languageLevelId) {
        LanguageLevel languageLevel = languageLevelService.languageLevelIdToLanguageLevel(languageLevelId);
        return languageLevelConverter.languageLevelToDto(languageLevel);
    }

    public Long getLanguageLevelId(LanguageLevelDto languageLevelDto) {
        return languageLevelService.getLanguageLevelId(languageLevelDto);
    }

    public LanguageLevelDto findLanguageLevel(Long languageId, Long levelId) {
        Long languageLevelId = languageLevelService.getLanguageLevelId(levelId, languageId);
        return languageLevelConverter.languageLevelToDto(languageLevelService.findLanguageLevel(languageLevelId));
    }
}
