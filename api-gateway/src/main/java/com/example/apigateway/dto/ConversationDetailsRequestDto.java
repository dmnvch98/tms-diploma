package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ConversationDetailsRequestDto {
    @NonFinal
    @Setter
    Long tutorId;
    Long conversationTypeId;
    Double price;
    Long addressId;
    Long languageId;
    Long minLevelId;
    String startDate;
    String endDate;
}
