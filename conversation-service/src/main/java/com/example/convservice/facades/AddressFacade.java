package com.example.convservice.facades;

import com.example.convservice.converters.AddressConverter;
import com.example.convservice.dto.AddressDto;
import com.example.convservice.model.Address;
import com.example.convservice.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
