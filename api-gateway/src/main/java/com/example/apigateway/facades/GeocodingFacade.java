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

    public Object getCoordinatesAddress(String latLng) {
        return geocodingService.getCoordinatesAddress(latLng, key);
    }

}
