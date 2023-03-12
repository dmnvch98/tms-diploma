package com.example.apigateway.client.maps;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "maps-service",
    url = "${services.maps.url}/api/geocode/json")
public interface GeocodingClient {

    @GetMapping()
    Object getCoordinatesAddress(@RequestParam("latlng") String latLng, @RequestParam("key") String key);
}
