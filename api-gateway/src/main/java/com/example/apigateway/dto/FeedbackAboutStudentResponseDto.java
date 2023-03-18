package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class FeedbackAboutStudentResponseDto {
    Long tutorId;
    Integer tutorRate;
    String tutorFeedback;
    Long languageId;

}
