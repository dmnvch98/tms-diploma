package com.example.convservice.converters.utils;

import com.example.convservice.model.ConversationDetails;

public interface FindConversationDetailsById {
    ConversationDetails findAllByConvDetailsId(Long convDetailsId);
}
