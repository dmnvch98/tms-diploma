package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class FilterTutorsRequestDto {
    Double minPrice;
    Double maxPrice;
    Long conversationTypeId;
    Long languageId;
    Long levelId;
    String city;
    Long countryId;
}
