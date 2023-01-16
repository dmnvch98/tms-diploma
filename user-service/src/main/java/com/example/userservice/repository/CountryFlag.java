package com.example.userservice.repository;

import com.example.userservice.model.Country;

public interface CountryFlag {
    Country findByCountryId(Long id);
}
