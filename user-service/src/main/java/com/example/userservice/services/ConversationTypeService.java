package com.example.userservice.services;

import com.example.userservice.converters.utils.FindConversationTypeById;
import com.example.userservice.model.ConversationType;
import com.example.userservice.repository.ConversationTypeRepository;
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
