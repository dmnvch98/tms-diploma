package com.example.conversationservice.facades;

import com.example.conversationservice.model.Address;
import com.example.conversationservice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressFacade {
    private final AddressService addressService;

    public Address save(Address address) {
        return addressService.save(address);
    }
}
