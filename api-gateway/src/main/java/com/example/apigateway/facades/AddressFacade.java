package com.example.apigateway.facades;

import com.example.apigateway.model.Address;
import com.example.apigateway.services.AddressService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AddressFacade {

    private final AddressService addressService;
    private final UserService userService;

    public Address saveAddress(Address address, Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        address.setTutorId(tutorId);
        return addressService.saveAddress(address);
    }

    public List<Address> findAllTutorAddresses(Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return addressService.findAllTutorAddresses(tutorId);
    }
}
