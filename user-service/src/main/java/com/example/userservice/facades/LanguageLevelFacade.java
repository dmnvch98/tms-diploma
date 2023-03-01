package com.example.userservice.facades;

import com.example.userservice.converters.LanguageLevelConverter;
import com.example.userservice.converters.utils.FindLanguageLevelById;
import com.example.userservice.converters.utils.FindLanguageLevelId;
import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageLevelFacade implements FindLanguageLevelById, FindLanguageLevelId {
    private final LanguageLevelService languageLevelService;
    private final LanguageLevelConverter languageLevelConverter;
    @Override
    public LanguageLevelDto languageLevelIdToLanguageLevelDto(Long languageLevelId) {
        LanguageLevel languageLevel = languageLevelService.languageLevelIdToLanguageLevel(languageLevelId);
        return languageLevelConverter.languageLevelToDto(languageLevel);
    }

    @Override
    public Long getLanguageLevelId(LanguageLevelDto languageLevelDto) {
        return languageLevelService.getLanguageLevelId(languageLevelDto);
    }
}
