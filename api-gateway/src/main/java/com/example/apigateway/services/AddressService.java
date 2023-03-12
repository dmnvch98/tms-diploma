package com.example.apigateway.services;

import com.example.apigateway.client.conversation.AddressClient;
import com.example.apigateway.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressClient addressClient;

    public List<AddressDto> findAddressesDistinctByTutorId(Long tutorId) {
        return addressClient.findAddressesDistinctByTutorId(tutorId);
    }
}
