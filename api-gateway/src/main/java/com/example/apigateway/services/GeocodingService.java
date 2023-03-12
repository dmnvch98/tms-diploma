package com.example.apigateway.services;

import com.example.apigateway.client.maps.GeocodingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeocodingService {

    private final GeocodingClient geocodingClient;

    public Object getCoordinatesAddress(String latLng, String key) {
        return geocodingClient.getCoordinatesAddress(latLng, key);
    }
}
