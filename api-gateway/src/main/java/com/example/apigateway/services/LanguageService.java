package com.example.apigateway.services;

import com.example.apigateway.client.LanguageClient;
import com.example.apigateway.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageClient languageClient;

    public List<Language> getAllLanguages () {
        return languageClient.getAllLanguages();
    }
}
