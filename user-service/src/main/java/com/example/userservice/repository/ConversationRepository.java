package com.example.userservice.repository;

import com.example.userservice.model.Conversation;
import org.springframework.data.repository.Repository;

public interface ConversationRepository extends Repository<Conversation, Long> {

    Conversation save(Conversation conversation);
}
