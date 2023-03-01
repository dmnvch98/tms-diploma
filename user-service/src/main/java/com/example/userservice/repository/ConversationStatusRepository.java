package com.example.userservice.repository;

import com.example.userservice.model.ConversationStatus;
import org.springframework.data.repository.Repository;

public interface ConversationStatusRepository extends Repository<ConversationStatus, Long> {
    ConversationStatus findAllByConvStatusId(Long convStatusId);
}
