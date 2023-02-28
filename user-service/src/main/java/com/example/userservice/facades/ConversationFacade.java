package com.example.userservice.facades;

import com.example.userservice.converters.ConversationConverter;
import com.example.userservice.dto.ConversationDetailsRequestDto;
import com.example.userservice.dto.ConversationDetailsResponseDto;
import com.example.userservice.model.ConversationDetails;
import com.example.userservice.services.ConversationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConversationFacade {
    private final ConversationDetailsService service;
    private final ConversationConverter converter;

    public ConversationDetailsResponseDto save(ConversationDetailsRequestDto conversationDetailsDto) {
        ConversationDetails conversationDetails =
            service.save(converter.dtoToConversationDetails(conversationDetailsDto));
        return converter.conversationDetailsToResponseDto(conversationDetails);
    }
}
