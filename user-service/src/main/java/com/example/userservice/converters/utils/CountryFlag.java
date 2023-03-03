package com.example.userservice.converters.utils;

import com.example.userservice.model.Country;

public interface CountryFlag {
    Country findByCountryId(Long id);
}
