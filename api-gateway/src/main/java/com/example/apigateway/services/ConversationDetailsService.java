package com.example.apigateway.services;

import com.example.apigateway.client.conversation.ConversationClient;
import com.example.apigateway.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationDetailsService {

    private final ConversationClient conversationClient;

    public double findTutorMinimumPrice(Long tutorId) {
        return conversationClient.findTutorMinimumPrice(tutorId);
    }

    public double findTutorMinimumPrice(FilterTutorsRequestDto dto, Long tutorId) {
        return conversationClient.findTutorMinimumPrice(dto, tutorId);
    }

    public int countAllTutorsWithConvDetails() {
        return conversationClient.countAllTutorsWithConvDetails();
    }

    public int countFilteredTutorsWithConvDetails(FilterTutorsRequestDto dto) {
        return conversationClient.countFilteredTutorsWithConvDetails(dto);
    }

    public ConversationDetailsResponseDto saveConversationDetails(ConversationDetailsRequestDto dto) {
        try {
            return conversationClient.saveConversationDetails(dto);
        } catch (Exception e) {
            log.error("An error occurred while saving conversation details: {}", e.getMessage());
        }
        return null;
    }

    public List<ConversationDetailsResponseDto> getTutorConversationDetails(Long tutorId) {
        try {
            return conversationClient.getTutorConversationDetails(tutorId);
        } catch (Exception e) {
            log.error("An error occurred while getting conversation details of tutorId {}: {}", tutorId, e.getMessage());
        }
        return null;
    }

    public ConversationResponseDto saveConversation(ConversationRequestDto dto) {
        try {
            return conversationClient.saveConversation(dto);
        } catch (Exception e) {
            log.error("An error occurred while booking the conversation with id {}: {}",
                dto.getConversationDetailsId(), e.getMessage());
        }
        return null;
    }

    public List<ConversationResponseDto> findAllByStudentId(Long studentId) {
        try {
            return conversationClient.findAllByStudentId(studentId);
        } catch (Exception e) {
            log.error("An error occurred while looking for the conversation for student id {}: {}",
                studentId, e.getMessage());
        }
        return null;
    }

    public List<ConversationResponseDto> findAllByTutorId(Long tutorId) {
        try {
            return conversationClient.findAllByTutorId(tutorId);
        } catch (Exception e) {
            log.error("An error occurred while looking for the conversation for tutor id {}: {}",
                tutorId, e.getMessage());
        }
        return null;
    }
}
