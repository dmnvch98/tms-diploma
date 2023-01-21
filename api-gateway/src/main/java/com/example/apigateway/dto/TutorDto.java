package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class TutorDto {
    Long tutorId;
    Long userId;
    String location;
    String aboutMe;
}
