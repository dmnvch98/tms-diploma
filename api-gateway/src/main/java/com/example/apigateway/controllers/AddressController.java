package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.facades.AddressFacade;
import com.example.apigateway.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
