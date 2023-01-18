package com.example.apigateway.controllers;

import com.example.apigateway.model.Level;
import com.example.apigateway.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {
    private final LanguageLevelService service;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Level>> getAllLanguages() {
        return ResponseEntity.ok(service.getAllLevels());
    }
}
