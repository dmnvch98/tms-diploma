package com.example.apigateway.facades;

import com.example.apigateway.services.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GeocodingFacade {

    private final GeocodingService geocodingService;

    @Value("${geocoding.key}")
    public String key;

    public Object getAddressByCoordinates(String latLng) {
        return geocodingService.getAddressByCoordinates(latLng, key);
    }

    public Object getCoordinatesByAddress(String address) {
        return geocodingService.getCoordinatesByAddress(address, key);
    }

    public Object getCoordinatesByAddress(String address) {
        return geocodingService.getCoordinatesByAddress(address, key);
    }

}
