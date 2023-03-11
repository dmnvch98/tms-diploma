package com.example.convservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "places-service",
    url = "${services.places.url}/")
public interface PlacesClient {
    @GetMapping()
    Object getCityInfo(@RequestParam("query") String query, @RequestParam("key") String key);
}
