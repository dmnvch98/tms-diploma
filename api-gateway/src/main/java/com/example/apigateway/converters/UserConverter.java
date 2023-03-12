package com.example.apigateway.converters;

import com.example.apigateway.dto.AddressDto;
import com.example.apigateway.dto.TutorCardInfoDto;
import com.example.apigateway.dto.TutorShortUserInfoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {
    TutorCardInfoDto tutorCardInfoToMinPrice(TutorShortUserInfoDto tutorCardInfo,
                                             double minPrice, List<AddressDto> addresses, String avatarUrl);
}
