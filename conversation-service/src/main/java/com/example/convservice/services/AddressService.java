package com.example.convservice.services;

import com.example.convservice.converters.utils.FindAddress;
import com.example.convservice.model.Address;
import com.example.convservice.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    public List<Address> findAllTutorAddresses(Long tutorId) {
        return addressRepository.findAllByTutorId(tutorId);
    }
}
