package com.example.apigateway.controllers;

import com.example.apigateway.facades.GeocodingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class MapsController {

    private final GeocodingFacade geocodingFacade;

    @GetMapping()
    public Object getCoordinatesAddress(@RequestParam("latlng") String latLng) {
        return geocodingFacade.getCoordinatesAddress(latLng);
    }
}
