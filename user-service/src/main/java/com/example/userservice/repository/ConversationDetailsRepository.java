package com.example.userservice.repository;

import com.example.userservice.model.ConversationDetails;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ConversationDetailsRepository extends Repository<ConversationDetails, Long> {

    ConversationDetails save(ConversationDetails conversationDetails);

    List<ConversationDetails> findAllByTutorId(Long tutorId);

    ConversationDetails findAllByConvDetailsId(Long convDetailsId);
}
