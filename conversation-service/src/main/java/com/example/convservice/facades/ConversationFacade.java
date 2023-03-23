package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.Conversation;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.services.CommandService;
import com.example.convservice.services.ConversationDetailsService;
import com.example.convservice.services.ConversationService;
import com.example.convservice.services.LanguageLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationFacade {

    private final ConversationService conversationService;
    private final ConversationConverter converter;
    private final ConversationDetailsService conversationDetailsService;
    private final LanguageLevelService languageLevelService;
    private final CommandService commandService;

    @Value("${conversations.init_status_id}")
    public Long conversationInitStatusId;

    @Value("${conversations.finished_status_id}")
    public Long conversationFinishedStatusId;

    public ConversationResponseDto save(ConversationRequestDto dto) {
        Conversation conversation = conversationService
            .save(converter.requestDtoToConversation(dto, conversationInitStatusId));

        LocalDateTime endDate = conversationDetailsService.findAllByConvDetailsId(conversation.getConversationDetailsId())
                .getEndDate();

        commandService.sendCommand(conversation.getConvId(), getDelayMillsBetweenEndDateAndNow(endDate));
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

    public Boolean countAllByConvIdAndStudentId(Long convId, Long studentId) {
        return conversationService.countAllByConvIdAndStudentId(convId, studentId);
    }

    public Boolean countAllByConvIdAndTutorId(Long convId, Long tutorId) {
        return conversationService.countAllByConvIdAndTutorId(convId, tutorId);
    }

    private Integer getDelayMillsBetweenEndDateAndNow(LocalDateTime endDate) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        return Math.toIntExact(Duration.between(dateTimeNow, endDate).toSeconds());
    }

    public Integer countStudentLessons(Long studentId) {
        return conversationService.countAllByStatusIdAndStudentId(conversationFinishedStatusId, studentId);
    }

    public Integer countTutorLessons(Long tutorId) {
        return conversationService.countAllByStatusIdAndTutorId(conversationFinishedStatusId, tutorId);
    }

}
