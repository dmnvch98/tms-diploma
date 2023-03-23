package com.example.apigateway.dto;

import com.example.apigateway.model.Address;
import com.example.apigateway.model.ConversationType;
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
    ConversationType conversationType;
    double price;
    Address address;
    LanguageLevelDto minLanguageLevel;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
