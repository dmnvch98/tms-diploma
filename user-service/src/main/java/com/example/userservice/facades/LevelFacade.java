package com.example.userservice.facades;

import com.example.userservice.model.Level;
import com.example.userservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LevelFacade {
    private final LanguageLevelService languageLevelService;

    public List<Level> findAllLevels() {
        return languageLevelService.findAllLevels();
    }

}
