package com.example.apigateway.services;

import com.example.apigateway.client.conversation.AddressClient;
import com.example.apigateway.dto.AddressDto;
import com.example.apigateway.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public List<AddressDto> findAddressesDistinctByTutorId(Long tutorId) {
        return addressClient.findAddressesDistinctByTutorId(tutorId);
    }

    public Address saveAddress(Address address) {
        return addressClient.saveAddress(address);
    }

    public List<Address> findAllTutorAddresses(Long tutorId) {
        return addressClient.findAllTutorAddresses(tutorId);
    }
}
