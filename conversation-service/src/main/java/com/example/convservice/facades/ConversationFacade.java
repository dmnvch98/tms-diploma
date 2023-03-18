package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.Conversation;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.services.ConversationDetailsService;
import com.example.convservice.services.ConversationService;
import com.example.convservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationFacade {

    private final ConversationService conversationService;
    private final ConversationConverter converter;
    private final ConversationDetailsService conversationDetailsService;
    private final LanguageLevelService languageLevelService;

    @Value("${conversations.initStatus}")
    public Long conversationInitStatus;

    public ConversationResponseDto save(ConversationRequestDto dto) {
        Conversation conversation = conversationService
            .save(converter.requestDtoToConversation(dto, conversationInitStatus));

        return findAllConversationInfo(conversation);
    }

    public List<ConversationResponseDto> findAllByStudentId(Long studentId) {
        return conversationService
            .findAllByStudentId(studentId)
            .stream()
            .map(this::findAllConversationInfo)
            .toList();
    }

    public List<ConversationResponseDto> findAllByTutorId(Long tutorId) {
        return conversationService
            .findAllByTutorId(tutorId)
            .stream()
            .map(this::findAllConversationInfo)
            .toList();
    }

    private ConversationResponseDto findAllConversationInfo(Conversation conversation) {
        ConversationDetails conversationDetails = conversationDetailsService
            .findAllByConvDetailsId(conversation.getConversationDetailsId());

        LanguageLevelDto languageLevelDto =
            languageLevelService.findLanguageLevelByLanguageIdAndLevelId(conversationDetails.getLanguageId(),
                conversationDetails.getMinLevelId());

        ConversationDetailsResponseDto conversationResponseDto =
            converter.conversationDetailsToResponseDto(conversationDetails, languageLevelDto);

        return converter.conversationToResponseDto(conversation, conversationResponseDto);
    }
}
