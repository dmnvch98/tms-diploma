package com.example.apigateway.facades;

import com.example.apigateway.model.Country;
import com.example.apigateway.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CountryFacade {

    private final CountryService countryService;
    @GetMapping
    public List<Country> findAllCountries() {
        return countryService.findAllCountries();
    }
}
