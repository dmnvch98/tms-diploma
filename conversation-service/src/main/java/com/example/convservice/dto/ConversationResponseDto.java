package com.example.convservice.dto;

import com.example.convservice.model.ConversationDetails;
import com.example.convservice.model.ConversationStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConversationResponseDto {
    Long id;
    ConversationStatus status;
    Long studentId;
    Boolean studentLeftFeedback;
    Boolean tutorLeftFeedback;
    ConversationDetailsResponseDto conversationDetails;
}
