package com.example.convservice.converters;

import com.example.convservice.dto.AddressDto;
import com.example.convservice.dto.TutorCardInfo;
import com.example.convservice.dto.TutorCardInfoMinPrice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {
    TutorCardInfoMinPrice tutorCardInfoToMinPrice(TutorCardInfo tutorCardInfo, double minPrice, List<AddressDto> addresses);
}
