package com.example.userservice.services;

import com.example.userservice.converters.utils.FindAddress;
import com.example.userservice.model.Address;
import com.example.userservice.repository.AddressRepository;
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
