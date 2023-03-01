package com.example.conversationservice.controllers;

import com.example.conversationservice.facades.AddressFacade;
import com.example.conversationservice.model.Address;
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
