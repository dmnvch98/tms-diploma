package com.example.userservice.facades;

import com.example.userservice.model.Address;
import com.example.userservice.services.AddressService;
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
