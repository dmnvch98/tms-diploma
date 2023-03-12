package com.example.apigateway.services;

import com.example.apigateway.client.conversation.ConversationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationDetailsService {

    private final ConversationClient conversationClient;

    public double findTutorMinimumPrice(Long tutorId) {
        return conversationClient.findTutorMinimumPrice(tutorId);
    }

    public int countAllTutorsWithConvDetails() {
        return conversationClient.countAllTutorsWithConvDetails();
    }
}
