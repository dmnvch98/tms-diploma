package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.dto.ConversationRequestDto;
import com.example.convservice.dto.ConversationResponseDto;
import com.example.convservice.model.Conversation;
import com.example.convservice.services.ConversationService;
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
