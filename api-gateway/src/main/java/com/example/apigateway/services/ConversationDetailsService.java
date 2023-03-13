package com.example.apigateway.services;

import com.example.apigateway.client.conversation.ConversationClient;
import com.example.convservice.dto.ConversationDetailsRequestDto;
import com.example.convservice.dto.ConversationDetailsResponseDto;
import com.example.convservice.dto.FilterTutorsRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public ConversationDetailsResponseDto saveConversationDetails(ConversationDetailsRequestDto dto) {
        try {
            return conversationClient.saveConversationDetails(dto);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
