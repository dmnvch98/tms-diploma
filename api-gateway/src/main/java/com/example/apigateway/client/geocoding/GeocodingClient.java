package com.example.apigateway.client.geocoding;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geocoding-service",
    url = "${services.maps.url}/api/geocode/json")
public interface GeocodingClient {

    @GetMapping()
    Object getAddressByCoordinates(@RequestParam("latlng") String latLng, @RequestParam("key") String key);

    @GetMapping()
    Object getCoordinatesByAddress(@RequestParam("address") String latLng, @RequestParam("key") String key);
}
