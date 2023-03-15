package com.example.convservice.controllers;

import com.example.convservice.dto.AddressDto;
import com.example.convservice.facades.AddressFacade;
import com.example.convservice.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressFacade addressFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Address saveAddress(@RequestBody Address address) {
        return addressFacade.save(address);
    }

    @GetMapping("/tutors/{tutorId}/conversation-details")
    public List<AddressDto> findAddressesDistinctByTutorId(@PathVariable("tutorId") Long tutorId) {
        return addressFacade.findAddressesDistinctByTutorId(tutorId);
    }

    @GetMapping("/tutors/{tutorId}")
    public List<Address> findAllTutorAddresses(@PathVariable("tutorId") Long tutorId) {
        return addressFacade.findAllTutorAddresses(tutorId);
    }
}
