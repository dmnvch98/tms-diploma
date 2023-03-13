package com.example.apigateway.client.maps;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "maps-service",
    url = "${services.maps.url}/api/geocode/json")
public interface GeocodingClient {

    @GetMapping()
    Object getAddressByCoordinates(@RequestParam("latlng") String latLng, @RequestParam("key") String key);

    @GetMapping()
    Object getCoordinatesByAddress(@RequestParam("address") String query, @RequestParam("key") String key);
}
