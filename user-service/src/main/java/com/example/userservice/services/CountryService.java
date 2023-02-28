package com.example.userservice.services;

import com.example.userservice.model.Country;
import com.example.userservice.converters.utils.CountryFlag;
import com.example.userservice.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService implements CountryFlag {

    private final CountryRepository countryRepository;

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country findByCountryId(Long countryId) {
        return countryRepository.findByCountryId(countryId);
    }
}
