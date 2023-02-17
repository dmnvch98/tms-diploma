package com.example.apigateway.client.user;

import com.example.apigateway.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service-countries",
        url = "${services.user.url}/api/v1/countries")
public interface CountryClient {
    @GetMapping
    List<Country> findAllCountries();
}
