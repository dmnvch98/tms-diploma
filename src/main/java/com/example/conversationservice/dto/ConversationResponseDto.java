package com.example.conversationservice.dto;

import com.example.conversationservice.model.ConversationStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConversationResponseDto {
    Long id;
    ConversationStatus status;
    Long studentId;
    ConversationDetailsResponseDto conversationDetails;

}
