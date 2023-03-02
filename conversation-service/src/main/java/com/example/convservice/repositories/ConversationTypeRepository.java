package com.example.convservice.repositories;

import com.example.convservice.model.ConversationType;
import org.springframework.data.repository.Repository;

public interface ConversationTypeRepository extends Repository<ConversationType, Long> {
    ConversationType getConversationTypeByConvTypeId(Long convTypeId);
}
