package com.example.conversationservice.repositories;

import com.example.conversationservice.model.Conversation;
import org.springframework.data.repository.Repository;

public interface ConversationRepository extends Repository<Conversation, Long> {

    Conversation save(Conversation conversation);
}
