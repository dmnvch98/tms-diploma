package com.example.userservice.dto;

import com.example.userservice.model.ConversationStatus;
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
