package com.example.userservice.services;

import com.example.userservice.model.ConversationDetails;
import com.example.userservice.repository.ConversationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationDetailsService {

    private final ConversationDetailsRepository repository;

    public ConversationDetails save(ConversationDetails conversationDetails) {
        return repository.save(conversationDetails);
    }
}
