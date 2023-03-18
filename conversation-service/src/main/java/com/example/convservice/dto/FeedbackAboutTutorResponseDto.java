package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class FeedbackAboutTutorResponseDto {
    Long feedbackId;
    Long studentId;
    Integer studentRate;
    String studentFeedback;
    Long languageId;
}
