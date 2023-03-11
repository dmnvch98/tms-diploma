package com.example.convservice.controllers;

import com.example.convservice.facades.AddressFacade;
import com.example.convservice.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressFacade addressFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Address save(@RequestBody Address address) {
        return addressFacade.save(address);
    }

    @CrossOrigin
    @GetMapping("/city-info")
    public Object getCityInfo(@RequestParam String query) {
        return addressFacade.getCityInfo(query);
    }
}
