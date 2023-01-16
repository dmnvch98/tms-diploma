package com.example.userservice.controllers;

import com.example.userservice.facades.LanguageFacade;
import com.example.userservice.model.Language;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageFacade languageFacade;
    private final LanguageLevelService languageLevelService;
    @GetMapping
    public List<Language> getLanguages() {
        return languageFacade.getAllLanguages();
    }

    @GetMapping("/level/{id}")
    public LanguageLevel findLanguageLevelById(@PathVariable("id") Long id) {
        return languageLevelService.findLanguageLevelById(id);
    }
}
