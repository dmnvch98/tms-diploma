package com.example.userservice.repository;

import com.example.userservice.model.Country;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CountryRepository extends Repository<Country, Long> {
    List<Country> findAll();

    Country findByCountryId(Long countryId);
}
