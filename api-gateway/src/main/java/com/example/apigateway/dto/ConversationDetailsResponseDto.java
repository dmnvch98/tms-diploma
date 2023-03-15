package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class ConversationDetailsResponseDto {
    Long convDetailsId;
    Long tutorId;
    Long conversationTypeId;
    double price;
    AddressDto address;
    LanguageLevelDto minimumLanguageLevel;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
