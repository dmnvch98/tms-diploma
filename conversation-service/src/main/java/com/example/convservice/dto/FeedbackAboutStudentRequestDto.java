package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class FeedbackAboutStudentRequestDto {
    Long conversationId;
    Integer tutorRate;
    String tutorFeedback;
}
