package com.example.userservice.services;

import com.example.userservice.converters.utils.FindConversationStatusById;
import com.example.userservice.model.ConversationStatus;
import com.example.userservice.repository.ConversationStatusRepository;
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
