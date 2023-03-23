package com.example.apigateway.services;

import com.example.apigateway.client.feedback.FeedbackClient;
import com.example.apigateway.dto.FeedbackAboutStudentRequestDto;
import com.example.apigateway.dto.FeedbackAboutStudentResponseDto;
import com.example.apigateway.dto.FeedbackAboutTutorRequestDto;
import com.example.apigateway.dto.FeedbackAboutTutorResponseDto;
import com.example.apigateway.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackClient feedbackClient;

    public Feedback saveFeedbackAboutStudent(FeedbackAboutStudentRequestDto dto) {
        return feedbackClient.saveFeedbackAboutStudent(dto);
    }

    public Feedback saveFeedbackAboutTutor(FeedbackAboutTutorRequestDto dto) {
        return feedbackClient.saveFeedbackAboutTutor(dto);
    }

    public List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(Long tutorId) {
        return feedbackClient.findFeedbacksAboutTutor(tutorId);
    }

    public List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(Long studentId) {
        return feedbackClient.findFeedbacksAboutStudent(studentId);
    }

}
