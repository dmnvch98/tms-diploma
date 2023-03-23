package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationDetailsRequestDto {
    Long tutorId;
    Long conversationTypeId;
    double price;
    Long addressId;
    LanguageLevelDto minimumLanguageLevel;
    String startDate;
    String endDate;
}
