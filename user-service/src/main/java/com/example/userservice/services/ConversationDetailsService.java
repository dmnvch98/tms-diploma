package com.example.userservice.services;

import com.example.userservice.converters.utils.FindConversationDetailsById;
import com.example.userservice.model.ConversationDetails;
import com.example.userservice.repository.ConversationDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationDetailsService implements FindConversationDetailsById {

    private final ConversationDetailsRepository repository;

    public ConversationDetails save(ConversationDetails conversationDetails) {
        return repository.save(conversationDetails);
    }

    public List<ConversationDetails> findAllByTutorId(Long tutorId) {
        return repository.findAllByTutorId(tutorId);
    }

    public ConversationDetails findAllByConvDetailsId(Long convDetailsId) {
        return repository.findAllByConvDetailsId(convDetailsId);
    }
}
