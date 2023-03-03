package com.example.convservice.services;

import com.example.convservice.converters.utils.FindConversationStatusById;
import com.example.convservice.model.ConversationStatus;
import com.example.convservice.repositories.ConversationStatusRepository;
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
