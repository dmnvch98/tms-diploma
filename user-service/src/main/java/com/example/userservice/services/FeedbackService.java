package com.example.userservice.services;

import com.example.userservice.client.conversation.FeedbackClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackClient feedbackClient;

    public Double findAvgRateForTutor(Long tutorId) {
        return feedbackClient.findAvgRateForTutor(tutorId);
    }

    public Double findAvgRateForStudent(Long studentId) {
        return feedbackClient.findAvgRateForStudent(studentId);
    }
}
