package com.example.userservice.controllers;

import com.example.userservice.facades.CountryFacade;
import com.example.userservice.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryFacade countryFacade;
    @GetMapping
    public List<Country> findAllCountries() {
        return countryFacade.findAllCountries();
    }
}
