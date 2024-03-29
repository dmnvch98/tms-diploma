package com.example.userservice.controllers;

import com.example.userservice.facades.LanguageFacade;
import com.example.userservice.model.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{languageId}")
    public Language findAllByLanguageId(@PathVariable("languageId") Long languageId) {
        return languageFacade.findAllByLanguageId(languageId);
    }
}
