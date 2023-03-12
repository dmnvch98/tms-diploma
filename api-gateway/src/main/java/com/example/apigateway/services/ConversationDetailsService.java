package com.example.apigateway.services;

import com.example.apigateway.client.conversation.ConversationClient;
import com.example.apigateway.dto.FilterTutorsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationDetailsService {

    private final ConversationClient conversationClient;

    public double findTutorMinimumPrice(Long tutorId) {
        return conversationClient.findTutorMinimumPrice(tutorId);
    }

    public double findTutorMinimumPrice(FilterTutorsRequestDto dto, Long tutorId) {
        return conversationClient.findTutorMinimumPrice(dto, tutorId);
    }

    public int countAllTutorsWithConvDetails() {
        return conversationClient.countAllTutorsWithConvDetails();
    }

    public int countFilteredTutorsWithConvDetails(FilterTutorsRequestDto dto) {
        return conversationClient.countFilteredTutorsWithConvDetails(dto);
    }
}
