package com.example.convservice.converters;

import com.example.convservice.dto.AddressDto;
import com.example.convservice.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AddressConverter {

    AddressDto addressToDto(Address address);
}
