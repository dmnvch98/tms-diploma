package com.example.conversationservice.repositories;

import com.example.conversationservice.model.Address;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address, Long> {
    Address save(final Address address);

    Address findAllByAddressId(Long addressId);
}
