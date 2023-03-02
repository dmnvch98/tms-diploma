package com.example.convservice.services;

import com.example.convservice.converters.utils.FindConversationTypeById;
import com.example.convservice.model.ConversationType;
import com.example.convservice.repositories.ConversationTypeRepository;
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
