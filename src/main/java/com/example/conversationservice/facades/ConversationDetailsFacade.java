package com.example.conversationservice.facades;

import com.example.conversationservice.converters.ConversationConverter;
import com.example.conversationservice.dto.ConversationDetailsRequestDto;
import com.example.conversationservice.dto.ConversationDetailsResponseDto;
import com.example.conversationservice.model.ConversationDetails;
import com.example.conversationservice.model.User;
import com.example.conversationservice.services.ConversationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationDetailsFacade {
    private final ConversationDetailsService service;
    private final ConversationConverter converter;

    public ConversationDetailsResponseDto save(ConversationDetailsRequestDto conversationDetailsDto) {
        ConversationDetails conversationDetails =
            service.save(converter.dtoToConversationDetails(conversationDetailsDto));
        return converter.conversationDetailsToResponseDto(conversationDetails);
    }

    public List<ConversationDetailsResponseDto> findAllByTutorId(Long tutorId) {
        return service.findAllByTutorId(tutorId).stream()
            .map(converter::conversationDetailsToResponseDto)
            .toList();
    }

    public List<User> findTutorsWithExistingConvDetails() {
        return service.findTutorsWithExistingConvDetails();
    }
}
