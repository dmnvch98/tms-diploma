package com.example.convservice.services;

import com.example.convservice.converters.utils.FindConversationDetailsById;
import com.example.convservice.model.ConversationDetails;
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

    public double findMinimumPriceByUserId(Long tutorId) {
        return repository.findMinimumPrice(tutorId);
    }

    public Double findMinimumPriceByUserId(Long tutorId, Long convTypeId, Long minLevel, Long languageId) {
        return repository.findMinimumPrice(tutorId, convTypeId, minLevel, languageId);
    }

    public int countAllTutorsWithConvDetails() {
        return repository.countTutorsWithConvDetails();
    }

    public int countFilteredTutorsWithConvDetails(Double minPrice, Double maxPrice, String city,
                                                  Long countryId, Long convTypeId, Long minLevel, Long languageId) {
        return repository.countFilteredTutorsWithConvDetails(minPrice, maxPrice, countryId, city, convTypeId,
            minLevel, languageId);
    }
}
