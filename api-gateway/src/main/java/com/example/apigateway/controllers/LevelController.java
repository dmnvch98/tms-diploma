package com.example.apigateway.controllers;

import com.example.apigateway.facades.LevelFacade;
import com.example.apigateway.model.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LevelFacade levelFacade;

    @GetMapping
    public List<Level> getAllLanguages() {
        return levelFacade.getAllLanguages();
    }
}
