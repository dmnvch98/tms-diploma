package com.example.userservice.controllers;

import com.example.userservice.facades.LevelFacade;
import com.example.userservice.model.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {

    private final LevelFacade levelFacade;

    @CrossOrigin
    @GetMapping
    public List<Level> getLanguages() {
        return levelFacade.findAllLevels();
    }

}
