package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class TutorCardInfoMinPrice {
    Long tutorId;
    String firstName;
    String lastName;
    List<LanguageLevelDto> languageLevels;
    double minPrice;
    List<AddressDto> addresses;

}
