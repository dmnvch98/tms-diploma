package com.example.apigateway.dto;

import com.example.apigateway.model.ConversationStatus;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationResponseDto {
    Long id;
    ConversationStatus status;
    Long studentId;
    ConversationDetailsResponseDto conversationDetails;

}
