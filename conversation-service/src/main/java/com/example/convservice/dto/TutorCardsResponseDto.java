package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TutorCardsResponseDto {
    int totalCount;
    List<TutorCardInfoDto> tutors;
}
