package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class TutorDto {
    Long tutorId;
    Long userId;
    String location;
    String aboutMe;
}
