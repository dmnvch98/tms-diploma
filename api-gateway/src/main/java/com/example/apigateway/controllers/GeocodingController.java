package com.example.apigateway.controllers;

import com.example.apigateway.facades.GeocodingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/geocoding")
public class GeocodingController {

    private final GeocodingFacade geocodingFacade;

    @GetMapping("/address")
    public Object getAddressByCoordinates(@RequestParam("latlng") String latLng) {
        return geocodingFacade.getAddressByCoordinates(latLng);
    }

    @GetMapping("/coordinates")
    public Object getCoordinatesByAddress(@RequestParam("address") String address) {
        return geocodingFacade.getCoordinatesByAddress(address);
    }
}
