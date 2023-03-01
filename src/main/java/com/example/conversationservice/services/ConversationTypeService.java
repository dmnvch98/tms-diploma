package com.example.conversationservice.services;

import com.example.conversationservice.converters.utils.FindConversationTypeById;
import com.example.conversationservice.model.ConversationType;
import com.example.conversationservice.repositories.ConversationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationTypeService implements FindConversationTypeById {

    private final ConversationTypeRepository repository;

    public ConversationType findById(Long id) {
        return repository.getConversationTypeByConvTypeId(id);
    }
}
