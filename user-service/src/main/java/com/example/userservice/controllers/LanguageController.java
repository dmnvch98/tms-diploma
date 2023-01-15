package com.example.userservice.controllers;

import com.example.userservice.facades.LanguageFacade;
import com.example.userservice.model.Language;
import lombok.RequiredArgsConstructor;
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
    public List<Language> getLanguages() {
        return languageFacade.getAllLanguages();
    }
}
