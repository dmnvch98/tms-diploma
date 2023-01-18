package com.example.apigateway.services;

import com.example.apigateway.client.LanguageClient;
import com.example.apigateway.client.LevelClient;
import com.example.apigateway.model.Language;
import com.example.apigateway.model.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageLevelService {
    private final LanguageClient languageClient;
    private final LevelClient levelClient;

    public List<Language> getAllLanguages() {
        return languageClient.getAllLanguages();
    }

    public List<Level> getAllLevels() {
        return levelClient.getAllLevels();
    }
}
