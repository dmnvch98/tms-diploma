package com.example.conversationservice.repositories;

import com.example.conversationservice.model.ConversationDetails;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ConversationDetailsRepository extends Repository<ConversationDetails, Long> {

    ConversationDetails save(ConversationDetails conversationDetails);

    List<ConversationDetails> findAllByTutorId(Long tutorId);

    ConversationDetails findAllByConvDetailsId(Long convDetailsId);
}
