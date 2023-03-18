package com.example.convservice.facades;

import com.example.convservice.converters.FeedbackConverter;
import com.example.convservice.dto.FeedbackAboutStudentRequestDto;
import com.example.convservice.dto.FeedbackAboutStudentResponseDto;
import com.example.convservice.dto.FeedbackAboutTutorRequestDto;
import com.example.convservice.dto.FeedbackAboutTutorResponseDto;
import com.example.convservice.model.Feedback;
import com.example.convservice.services.ConversationService;
import com.example.convservice.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FeedbackFacade {

    private final FeedbackService feedbackService;
    private final FeedbackConverter feedbackConverter;
    private final ConversationService conversationService;

    public Feedback saveFeedbackAboutStudent(FeedbackAboutStudentRequestDto dto) {
        Integer updatedFeedbacksRowsCount =
            feedbackService.saveFeedbackAboutStudent(dto.getConversationId(), dto.getTutorFeedback(), dto.getTutorRate());

        Integer updatedConversationRowsCount =
            conversationService.updateTutorLeftFeedbackFlag(dto.getConversationId());

        return updatedFeedbacksRowsCount == 1 && updatedConversationRowsCount == 1
            ? feedbackService.findAllByConversationId(dto.getConversationId())
            : null;
    }

    public Feedback saveFeedbackAboutTutor(FeedbackAboutTutorRequestDto dto) {
        Integer updatedRowsCount =
            feedbackService.saveFeedbackAboutTutor(dto.getConversationId(), dto.getStudentFeedback(), dto.getStudentRate());

        Integer updatedConversationRowsCount =
            conversationService.updateStudentLeftFeedbackFlag(dto.getConversationId());

        return updatedRowsCount == 1 && updatedConversationRowsCount == 1
            ? feedbackService.findAllByConversationId(dto.getConversationId())
            : null;
    }

    public List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(Long tutorId) {
        return feedbackService
            .findFeedbacksAboutTutor(tutorId)
            .stream()
            .map(feedback -> feedbackConverter
                .feedbackToFeedbackAboutTutor(feedback,

                    conversationService
                        .findStudentIdByConversationId(feedback.getConversationId()),

                    conversationService
                        .findLanguageIdByConversationId(feedback.getConversationId())))
            .toList();
    }

    public List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(Long studentId) {
        return feedbackService
            .findFeedbacksAboutStudent(studentId)
            .stream()
            .map(feedback -> feedbackConverter
                .feedbackToFeedbackAboutStudent(feedback,

                    conversationService
                        .findTutorIdByConversationId(feedback.getConversationId()),

                    conversationService
                        .findLanguageIdByConversationId(feedback.getConversationId())))
            .toList();
    }

    public Double findAvgRateForTutor(Long tutorId) {
        return feedbackService.findAvgRateForTutor(tutorId);
    }

    public Double findAvgRateForStudent(Long studentId) {
        return feedbackService.findAvgRateForStudent(studentId);
    }
}
