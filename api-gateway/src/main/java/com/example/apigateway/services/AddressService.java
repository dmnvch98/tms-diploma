package com.example.apigateway.services;

import com.example.apigateway.client.conversation.AddressClient;
import com.example.apigateway.dto.AddressDto;
import com.example.apigateway.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressClient addressClient;

    public List<AddressDto> findAddressesDistinctByTutorId(Long tutorId) {
        try {
            return addressClient.findAddressesDistinctByTutorId(tutorId);
        } catch (Exception e) {
            log.error("An error occurred during unique addresses fetching {}", e.getMessage());
        }
        return null;
    }


    public Address saveAddress(Address address) {
        return addressClient.saveAddress(address);
    }

    public List<Address> findAllTutorAddresses(Long tutorId) {
        try {
            return addressClient.findAllTutorAddresses(tutorId);
        } catch (Exception e) {
            log.error("An error occurred during addresses fetching {}", e.getMessage());
        }
        return null;
    }
}
