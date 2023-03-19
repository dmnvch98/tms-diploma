package com.example.convservice.services;

import com.example.convservice.model.Conversation;
import com.example.convservice.repositories.ConversationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public List<Conversation> findAllByStudentId(Long studentId) {
        return conversationRepository.findAllByStudentIdOrderByConvIdDesc(studentId);
    }

    public List<Conversation> findAllByTutorId(Long tutorId) {
        return conversationRepository.findAllByTutorId(tutorId);
    }

    public Long findStudentIdByConversationId(Long convId) {
        return conversationRepository.findStudentIdByConversationId(convId);
    }


    public Long findTutorIdByConversationId(Long convId) {
        return conversationRepository.findTutorIdByConversationId(convId);
    }

    public Long findLanguageIdByConversationId(Long convId) {
        return conversationRepository.findLanguageIdByConversationId(convId);
    }

    public Boolean countAllByConvIdAndStudentId(Long convId, Long studentId) {
        return conversationRepository.countAllByConvIdAndStudentId(convId, studentId) == 1;
    }

    public Boolean countAllByConvIdAndTutorId(Long convId, Long tutorId) {
        return conversationRepository.countAllByConvIdAndTutorId(convId, tutorId) == 1;
    }

    public Integer updateStudentLeftFeedbackFlag(Long convId) {
        return conversationRepository.updateStudentLeftFeedbackFlag(convId);
    }


    public Integer updateTutorLeftFeedbackFlag(Long convId) {
        return conversationRepository.updateTutorLeftFeedbackFlag(convId);
    }

    public void updateConversationStatusToFinish(@Param("convId") Long convId) {
        try {
            log.info("Changing conversation status to Finished. Conversation ID: {}", convId);
            int updatedRows = conversationRepository.updateConversationStatusToFinish(convId);

            if (updatedRows == 1) {
                log.info("Conversation status successfully updated to Finished. Conversation ID: {}", convId);
            } else {
                log.warn("Returned updated rows count not 1 after updating conversation status to Finished. " +
                    "Conversation ID: {}. Returned count: {}", convId, updatedRows);
            }
        }
        catch (Exception e) {
            log.error("An error occurred during updating conversation status to Finished. Conversation ID: {} . Error: {}",
                convId, e.getMessage());
            throw new RuntimeException();
        }
    }
}
