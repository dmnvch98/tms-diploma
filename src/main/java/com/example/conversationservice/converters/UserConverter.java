package com.example.conversationservice.converters;

import com.example.conversationservice.dto.AddressDto;
import com.example.conversationservice.dto.TutorCardInfo;
import com.example.conversationservice.dto.TutorCardInfoMinPrice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {
    TutorCardInfoMinPrice tutorCardInfoToMinPrice(TutorCardInfo tutorCardInfo, double minPrice, List<AddressDto> addresses);
}
