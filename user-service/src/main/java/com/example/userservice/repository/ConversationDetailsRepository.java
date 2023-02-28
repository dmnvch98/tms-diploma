package com.example.userservice.repository;

import com.example.userservice.model.ConversationDetails;
import org.springframework.data.repository.Repository;

public interface ConversationDetailsRepository extends Repository<ConversationDetails, Long> {

    ConversationDetails save(ConversationDetails conversationDetails);
}
