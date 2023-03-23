package com.example.convservice.converters;

import com.example.convservice.dto.FeedbackAboutStudentResponseDto;
import com.example.convservice.dto.FeedbackAboutTutorResponseDto;
import com.example.convservice.model.Feedback;
import org.mapstruct.Mapper;

@Mapper
public interface FeedbackConverter {
    FeedbackAboutStudentResponseDto feedbackToFeedbackAboutStudent(Feedback feedback, Long tutorId, Long languageId);

    FeedbackAboutTutorResponseDto feedbackToFeedbackAboutTutor(Feedback feedback, Long studentId, Long languageId);
}
