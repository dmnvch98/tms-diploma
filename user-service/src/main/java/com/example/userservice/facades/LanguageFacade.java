package com.example.userservice.facades;

import com.example.userservice.model.Language;
import com.example.userservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LanguageFacade {

    private final LanguageLevelService languageLevelService;

    public List<Language> getAllLanguages() {
        return languageLevelService.findAllLanguages();
    }

    public Language findAllByLanguageId(Long languageId) {
        return languageLevelService.findAllByLanguageId(languageId);
    }

}
