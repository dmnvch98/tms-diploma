package com.example.userservice.converters;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.Country;
import com.example.userservice.model.User;
import com.example.userservice.repository.CountryFlag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CountryFlag.class})
public interface UserConverter {

    UserDto userToDto(User user);

    default Long countryToId(Country country) {
        return country.getCountryId();
    }

    @Mapping(target = "nationality", source = "nationality")
    User dtoToUser(UserDto userDto);
}
