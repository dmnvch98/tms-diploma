package com.example.conversationservice.facades;

import com.example.conversationservice.converters.ConversationConverter;
import com.example.conversationservice.dto.ConversationRequestDto;
import com.example.conversationservice.dto.ConversationResponseDto;
import com.example.conversationservice.model.Conversation;
import com.example.conversationservice.services.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConversationFacade {

    private final ConversationService conversationService;
    private final ConversationConverter converter;
    @Value("${conversations.initStatus}")
    public Long conversationInitStatus;

    public ConversationResponseDto save(ConversationRequestDto dto) {
        Conversation conversation = conversationService
            .save(converter.requestDtoToConversation(dto, conversationInitStatus));
        return converter.conversationToResponseDto(conversation);
    }
}
