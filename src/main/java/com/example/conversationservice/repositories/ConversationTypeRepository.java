package com.example.conversationservice.repositories;

import com.example.conversationservice.model.ConversationType;
import org.springframework.data.repository.Repository;

public interface ConversationTypeRepository extends Repository<ConversationType, Long> {
    ConversationType getConversationTypeByConvTypeId(Long convTypeId);
}
