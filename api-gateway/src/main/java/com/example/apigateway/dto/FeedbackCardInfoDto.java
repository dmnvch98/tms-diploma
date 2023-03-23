package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FeedbackCardInfoDto {
    Long feedbackId;
    String firstName;
    String lastName;
    Long tutorId;
    Long studentId;
    Integer rate;
    String feedback;
    String languageDescription;
    String userAvatarUrl;

}
