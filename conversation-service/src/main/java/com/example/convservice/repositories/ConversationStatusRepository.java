package com.example.convservice.repositories;


import com.example.convservice.model.ConversationStatus;
import org.springframework.data.repository.Repository;

public interface ConversationStatusRepository extends Repository<ConversationStatus, Long> {
    ConversationStatus findAllByConvStatusId(Long convStatusId);
}
