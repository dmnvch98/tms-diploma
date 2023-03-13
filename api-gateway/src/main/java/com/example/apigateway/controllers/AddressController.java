package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.facades.AddressFacade;
import com.example.apigateway.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/addresses")
@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressFacade addressFacade;

    @PostMapping("/")
    Address saveAddress(@RequestBody Address address, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return addressFacade.saveAddress(address, userId);
    }

    @GetMapping("/tutors/{tutorId}")
    public List<Address> findAllTutorAddresses(@PathVariable("tutorId") Long tutorId) {
        return addressFacade.findAllTutorAddresses(tutorId);
    }
}
