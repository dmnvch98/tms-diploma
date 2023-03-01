package com.example.userservice.facades;

import com.example.userservice.converters.ConversationConverter;
import com.example.userservice.dto.ConversationRequestDto;
import com.example.userservice.dto.ConversationResponseDto;
import com.example.userservice.model.Conversation;
import com.example.userservice.services.ConversationService;
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
