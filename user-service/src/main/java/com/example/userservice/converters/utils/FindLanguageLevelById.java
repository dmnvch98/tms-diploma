package com.example.userservice.converters.utils;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.LanguageLevel;

public interface FindLanguageLevelById {
    LanguageLevelDto languageLevelIdToLanguageLevelDto(Long languageLevelId);
}
