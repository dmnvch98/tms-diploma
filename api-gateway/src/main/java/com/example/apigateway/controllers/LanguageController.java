package com.example.apigateway.controllers;

import com.example.apigateway.model.Language;
import com.example.apigateway.services.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageService languageService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }
}
