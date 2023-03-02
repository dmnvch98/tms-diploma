package com.example.convservice.repositories;

import com.example.convservice.model.Address;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends Repository<Address, Long> {
    Address save(final Address address);

    Address findAllByAddressId(Long addressId);

    @Query("select distinct a.* from addresses a" +
        " join conv_details cd on a.address_id = cd.address_id" +
        " where cd.tutor_id=:tutorId")
    List<Address> findDistinctByTutorId(@Param("tutorId") Long tutorId);
}
