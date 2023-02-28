package com.example.userservice.dto;

import com.example.userservice.model.Address;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class ConversationDetailsResponseDto {
    Long tutorId;
    Long conversationTypeId;
    double price;
    Address address;
    LanguageLevelDto minimumLanguageLevel;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
