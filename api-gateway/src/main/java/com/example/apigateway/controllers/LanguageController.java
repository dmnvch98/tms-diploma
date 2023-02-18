package com.example.apigateway.controllers;

import com.example.apigateway.facades.LanguageFacade;
import com.example.apigateway.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageFacade languageFacade;

    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok(languageFacade.getAllLanguages());
    }
}
