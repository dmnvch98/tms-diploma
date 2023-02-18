package com.example.apigateway.facades;

import com.example.apigateway.model.Level;
import com.example.apigateway.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LevelFacade {

    private final LanguageLevelService service;

    public List<Level> getAllLanguages() {
        return service.getAllLevels();
    }
}
