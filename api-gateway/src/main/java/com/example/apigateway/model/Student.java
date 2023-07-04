package com.example.apigateway.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Student {
    Long studentId;
    Long userId;
    String aboutMe;
    @NonFinal
    @Setter
    String presentationUrl;
}
