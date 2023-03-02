package com.example.convservice.repositories;


import com.example.convservice.model.Conversation;
import org.springframework.data.repository.Repository;

public interface ConversationRepository extends Repository<Conversation, Long> {

    Conversation save(Conversation conversation);
}
