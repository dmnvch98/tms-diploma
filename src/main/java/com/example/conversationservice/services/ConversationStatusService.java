package com.example.conversationservice.services;

import com.example.conversationservice.converters.utils.FindConversationStatusById;
import com.example.conversationservice.model.ConversationStatus;
import com.example.conversationservice.repositories.ConversationStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationStatusService implements FindConversationStatusById {

    private final ConversationStatusRepository repository;

    public ConversationStatus findConversationStatus(Long id) {
        return repository.findAllByConvStatusId(id);
    }
}
