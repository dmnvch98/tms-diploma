package com.example.userservice.converters;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.Country;
import com.example.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryFlag.class})
public interface UserConverter {

    UserRequestDto userToDto(User user);

    default Long countryToId(Country country) {
        return country.getCountryId();
    }

    @Mapping(target = "nationality", source = "nationality")
    User dtoToUser(UserRequestDto userDto);

    UserResponseDto userToResponseDto(User user);

}
