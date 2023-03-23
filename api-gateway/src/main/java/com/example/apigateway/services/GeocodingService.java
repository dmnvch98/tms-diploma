package com.example.apigateway.services;

import com.example.apigateway.client.geocoding.GeocodingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeocodingService {

    private final GeocodingClient geocodingClient;

    public Object getAddressByCoordinates(String latLng, String key) {
        try {
            return geocodingClient.getAddressByCoordinates(latLng, key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public Object getCoordinatesByAddress(String address, String key) {
        try {
            return geocodingClient.getCoordinatesByAddress(address, key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
