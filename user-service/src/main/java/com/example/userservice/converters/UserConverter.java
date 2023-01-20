package com.example.userservice.converters;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.model.Country;
import com.example.userservice.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CountryFlag.class})
public interface UserConverter {

    default Long countryToId(Country country) {
        return country.getCountryId();
    }

    @Mapping(target = "nationality", source = "nationality")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User userRequestDtoToUser(UserRequestDto userDto);

    UserResponseDto userToResponseDto(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUser(UserRequestDto userRequestDto, @MappingTarget User.UserBuilder user);
}
