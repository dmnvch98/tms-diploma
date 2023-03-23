package com.example.convservice.services;

import com.example.convservice.converters.utils.FindConversationDetailsById;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.model.User;
import com.example.convservice.repositories.ConversationDetailsRepository;
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

    public double findMinimumPriceByUserId(Long tutorId) {
        return repository.findMinimumPriceByUserId(tutorId);
    }
}
