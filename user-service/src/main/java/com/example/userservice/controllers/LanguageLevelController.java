package com.example.userservice.controllers;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.facades.LanguageLevelFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/language-levels")
public class LanguageLevelController {
    private final LanguageLevelFacade languageLevelFacade;

    @GetMapping("/{languageLevelId}")
    LanguageLevelDto findLanguageLevelById(@PathVariable Long languageLevelId){
        return languageLevelFacade.languageLevelIdToLanguageLevelDto(languageLevelId);
    }

    @GetMapping("/")
    Long findLanguageLevelId(@RequestBody LanguageLevelDto languageLevelDto) {
        return languageLevelFacade.getLanguageLevelId(languageLevelDto);
    }
}
