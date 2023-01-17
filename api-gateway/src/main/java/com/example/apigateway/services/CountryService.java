package com.example.apigateway.services;

import com.example.apigateway.client.CountryClient;
import com.example.apigateway.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryClient countryClient;

    public List<Country> findAllCountries() {
        return countryClient.findAllCountries();
    }
}
