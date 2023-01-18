package com.example.userservice.facades;

import com.example.userservice.model.Country;
import com.example.userservice.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CountryFacade {
    private final CountryService countryService;

    public List<Country> findAllCountries() {
        return countryService.findAllCountries();
    }
}
