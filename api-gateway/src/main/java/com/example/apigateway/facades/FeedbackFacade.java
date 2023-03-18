package com.example.apigateway.facades;

import com.example.apigateway.dto.FeedbackAboutStudentRequestDto;
import com.example.apigateway.dto.FeedbackAboutStudentResponseDto;
import com.example.apigateway.dto.FeedbackAboutTutorRequestDto;
import com.example.apigateway.dto.FeedbackAboutTutorResponseDto;
import com.example.apigateway.model.Feedback;
import com.example.apigateway.services.ConversationDetailsService;
import com.example.apigateway.services.FeedbackService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FeedbackFacade {
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final ConversationDetailsService conversationDetailsService;

    public Feedback saveFeedbackAboutStudent(FeedbackAboutStudentRequestDto dto, Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return conversationDetailsService
            .countAllByConversationDetailsIdAndTutorId(dto.getConversationId(), tutorId)
            ? feedbackService.saveFeedbackAboutStudent(dto)
            : null;
    }

    public Feedback saveFeedbackAboutTutor(FeedbackAboutTutorRequestDto dto, Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return conversationDetailsService
            .countAllByConvIdAndStudentId(dto.getConversationId(), studentId)
            ? feedbackService.saveFeedbackAboutTutor(dto)
            : null;
    }

    public List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(Long tutorId) {
        return feedbackService.findFeedbacksAboutTutor(tutorId);
    }

    public List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(Long studentId) {
        return feedbackService.findFeedbacksAboutStudent(studentId);
    }
}
