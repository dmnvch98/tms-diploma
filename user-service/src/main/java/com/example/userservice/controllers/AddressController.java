package com.example.userservice.controllers;

import com.example.userservice.facades.AddressFacade;
import com.example.userservice.model.Address;
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
}
