package com.example.apigateway.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Student {
    Long studentId;
    Long userId;
    String location;
    String aboutMe;
}
