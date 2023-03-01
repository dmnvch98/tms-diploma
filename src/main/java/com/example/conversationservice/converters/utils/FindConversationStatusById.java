package com.example.conversationservice.converters.utils;

import com.example.conversationservice.model.ConversationStatus;

public interface FindConversationStatusById {
    ConversationStatus findConversationStatus(Long id);
}
