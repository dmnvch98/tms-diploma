package com.example.userservice.converters.utils;

import com.example.userservice.model.ConversationDetails;

public interface FindConversationDetailsById {
    ConversationDetails findAllByConvDetailsId(Long convDetailsId);
}
