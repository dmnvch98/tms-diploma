package com.example.conversationservice.converters.utils;

import com.example.conversationservice.model.ConversationDetails;

public interface FindConversationDetailsById {
    ConversationDetails findAllByConvDetailsId(Long convDetailsId);
}
