package com.example.convservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationDetailsRequestDto {
    Long tutorId;
    Long conversationTypeId;
    Double price;
    Long addressId;
    Long languageId;
    Long minLevelId;
    String startDate;
    String endDate;
}
