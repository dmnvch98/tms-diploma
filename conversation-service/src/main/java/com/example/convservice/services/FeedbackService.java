package com.example.convservice.services;

import com.example.convservice.model.Feedback;
import com.example.convservice.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Integer saveFeedbackAboutStudent(Long convId, String tutorFeedback, Integer tutorRate) {
        return feedbackRepository.saveFeedbackAboutStudent(convId, tutorFeedback, tutorRate);
    }

    public Integer saveFeedbackAboutTutor(Long convId, String studentFeedback, Integer studentRate) {
        return feedbackRepository.saveFeedbackAboutTutor(convId, studentFeedback, studentRate);
    }

    public Feedback findAllByConversationId(Long findAllByConversationId) {
        return feedbackRepository.findAllByConversationId(findAllByConversationId);
    }

    public List<Feedback> findFeedbacksAboutTutor(Long tutorId) {
        return feedbackRepository.findFeedbacksAboutTutor(tutorId);
    }

    public List<Feedback> findFeedbacksAboutStudent(Long studentId) {
        return feedbackRepository.findFeedbacksAboutStudent(studentId);
    }

    public Double findAvgRateForTutor(Long tutorId) {
        return Optional.ofNullable(feedbackRepository.findAvgRateForTutor(tutorId)).orElse(0.0);
    }

    public Double findAvgRateForStudent(Long studentId) {
        return Optional.ofNullable(feedbackRepository.findAvgRateForStudent(studentId)).orElse(0.0);
    }
}
