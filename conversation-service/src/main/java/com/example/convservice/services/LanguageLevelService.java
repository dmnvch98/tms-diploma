package com.example.convservice.services;

import com.example.convservice.client.LanguageLevelClient;
import com.example.convservice.dto.LanguageLevel;
import com.example.convservice.dto.LanguageLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageLevelService {
    private final LanguageLevelClient client;

    public LanguageLevelDto findLanguageLevelByLanguageIdAndLevelId(Long languageId, Long levelId) {
        return client.findLanguageLevelByLanguageIdAndLevelId(languageId, levelId);
    }



}
