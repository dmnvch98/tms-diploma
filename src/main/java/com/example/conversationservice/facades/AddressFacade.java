package com.example.conversationservice.facades;

import com.example.conversationservice.converters.AddressConverter;
import com.example.conversationservice.dto.AddressDto;
import com.example.conversationservice.model.Address;
import com.example.conversationservice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressFacade {
    private final AddressService addressService;
    private final AddressConverter addressConverter;

    public Address save(Address address) {
        return addressService.save(address);
    }

    public List<AddressDto> findAddressesDistinctByTutorId(Long tutorId) {
        return addressService
            .findAddressesDistinctByTutorId(tutorId)
            .stream()
            .map(addressConverter::addressToDto)
            .toList();
    }
}
