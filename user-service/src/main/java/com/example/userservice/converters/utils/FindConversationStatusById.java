package com.example.userservice.converters.utils;

import com.example.userservice.model.ConversationStatus;

public interface FindConversationStatusById {
    ConversationStatus findConversationStatus(Long id);
}
