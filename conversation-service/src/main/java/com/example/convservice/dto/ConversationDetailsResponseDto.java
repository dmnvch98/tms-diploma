package com.example.convservice.dto;

import com.example.convservice.model.Address;
import com.example.convservice.model.ConversationType;
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
    LanguageLevelDto minimumLanguageLevel;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
