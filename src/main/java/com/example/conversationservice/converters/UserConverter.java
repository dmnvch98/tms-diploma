package com.example.conversationservice.converters;

import com.example.conversationservice.dto.TutorCardInfo;
import com.example.conversationservice.dto.TutorCardInfoMinPrice;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {
    TutorCardInfoMinPrice tutorCardInfoToMinPrice(TutorCardInfo tutorCardInfo, double minPrice);
}
