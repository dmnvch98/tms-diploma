package com.example.apigateway.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Feedback {
    Long feedbackId;
    Long conversationId;
    Integer tutorRate;
    Integer studentRate;
    String tutorFeedback;
    String studentFeedback;
}
