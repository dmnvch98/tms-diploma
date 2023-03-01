package com.example.conversationservice.services;

import com.example.conversationservice.converters.utils.FindConversationDetailsById;
import com.example.conversationservice.model.ConversationDetails;
import com.example.conversationservice.model.User;
import com.example.conversationservice.repositories.ConversationDetailsRepository;
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

    public List<User> filterTutors(double minPrice, double maxPrice, Long convTypeId,
                                   String location, Long languageId, Long levelId) {
        return repository.filterTutors(minPrice, maxPrice, convTypeId, location, languageId, levelId);
    }
}
