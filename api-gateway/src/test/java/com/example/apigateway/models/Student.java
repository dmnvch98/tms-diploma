package com.example.apigateway.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class Student {
    Long studentId;
    Long userId;
    String aboutMe;
    String presentationFileName;
}
