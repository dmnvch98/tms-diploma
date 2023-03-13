package com.example.apigateway.dto;

import com.example.convservice.dto.AddressDto;
import com.example.userservice.dto.LanguageLevelDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class TutorCardInfoDto {
    Long tutorId;
    String firstName;
    String lastName;
    List<LanguageLevelDto> languageLevels;
    double minPrice;
    List<AddressDto> addresses;
    String avatarUrl;

}
