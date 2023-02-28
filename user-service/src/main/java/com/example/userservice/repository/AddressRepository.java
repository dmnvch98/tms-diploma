package com.example.userservice.repository;

import com.example.userservice.model.Address;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address, Long> {
    Address save(final Address address);

    Address findAllByAddressId(Long addressId);
}
