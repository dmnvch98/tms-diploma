package com.example.conversationservice.repositories;

import com.example.conversationservice.model.ConversationStatus;
import org.springframework.data.repository.Repository;

public interface ConversationStatusRepository extends Repository<ConversationStatus, Long> {
    ConversationStatus findAllByConvStatusId(Long convStatusId);
}
