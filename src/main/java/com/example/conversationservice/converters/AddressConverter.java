package com.example.conversationservice.converters;

import com.example.conversationservice.dto.AddressDto;
import com.example.conversationservice.model.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressConverter {

    AddressDto addressToDto(Address address);
}
