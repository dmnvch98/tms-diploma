package com.example.apigateway.facades;

import com.example.apigateway.model.Language;
import com.example.apigateway.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LanguageFacade {
    private final LanguageLevelService languageService;

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
}
