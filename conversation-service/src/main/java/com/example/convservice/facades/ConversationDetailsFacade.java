package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.services.ConversationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationDetailsFacade {
    private final ConversationDetailsService conversationDetailsService;
    private final ConversationConverter converter;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public ConversationDetailsResponseDto save(ConversationDetailsRequestDto conversationDetailsDto) {
        ConversationDetails conversationDetails =
            conversationDetailsService.save(converter.dtoToConversationDetails(conversationDetailsDto));
        return converter.conversationDetailsToResponseDto(conversationDetails);
    }

    public List<ConversationDetailsResponseDto> findAllByTutorId(Long tutorId) {
        return conversationDetailsService.findAllByTutorId(tutorId).stream()
            .map(converter::conversationDetailsToResponseDto)
            .toList();
    }

    public double findTutorMinimumPrice(Long tutorId) {
        return conversationDetailsService.findMinimumPriceByUserId(tutorId);
    }

    public double findTutorMinimumPrice(Long tutorId, FilterTutorsRequestDto dto) {
        return conversationDetailsService.findMinimumPriceByUserId(tutorId, dto.getConversationTypeId(),
            dto.getLevelId(), dto.getLanguageId());
    }

    public int countAllTutorsWithConvDetails() {
        return conversationDetailsService.countAllTutorsWithConvDetails();
    }

    public int countFilteredTutorsWithConvDetails(FilterTutorsRequestDto dto) {
        return conversationDetailsService.countFilteredTutorsWithConvDetails(dto.getMinPrice(), dto.getMaxPrice(),
            dto.getCity(), dto.getCountryId(), dto.getConversationTypeId(), dto.getLevelId(), dto.getLanguageId());
    }
}
