package com.example.conversationservice.services;

import com.example.conversationservice.converters.utils.FindAddress;
import com.example.conversationservice.model.Address;
import com.example.conversationservice.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Address> findAddressesDistinctByTutorId(Long tutorId) {
        return addressRepository.findDistinctByTutorId(tutorId);
    }
}
