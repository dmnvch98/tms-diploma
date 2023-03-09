package com.example.convservice.converters;

import com.example.convservice.dto.AddressDto;
import com.example.convservice.dto.TutorShortUserInfoDto;
import com.example.convservice.dto.TutorCardInfoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {
    TutorCardInfoDto tutorCardInfoToMinPrice(TutorShortUserInfoDto tutorCardInfo,
                                             double minPrice, List<AddressDto> addresses, String avatarUrl);
}
