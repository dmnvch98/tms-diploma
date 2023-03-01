package com.example.conversationservice.services;

import com.example.conversationservice.converters.utils.FindAddress;
import com.example.conversationservice.model.Address;
import com.example.conversationservice.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService implements FindAddress {
    private final AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public Address findAddressById(Long addressId) {
        return addressRepository.findAllByAddressId(addressId);
    }
}
