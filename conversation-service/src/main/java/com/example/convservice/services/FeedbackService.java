package com.example.convservice.services;

import com.example.convservice.model.Feedback;
import com.example.convservice.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Integer saveTutorFeedback(Long convId, String tutorFeedback, Integer tutorRate) {
        return feedbackRepository.saveTutorFeedback(convId, tutorFeedback, tutorRate);
    }

    public Integer saveStudentFeedback(Long convId, String studentFeedback, Integer studentRate) {
        return feedbackRepository.saveStudentFeedback(convId, studentFeedback, studentRate);
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
}
