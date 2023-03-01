package com.example.userservice.repository;

import com.example.userservice.model.ConversationType;
import org.springframework.data.repository.Repository;

public interface ConversationTypeRepository extends Repository<ConversationType, Long> {
    ConversationType getConversationTypeByConvTypeId(Long convTypeId);
}
