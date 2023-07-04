package com.example.apigateway.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Tutor {
    Long tutorId;
    Long userId;
    String aboutMe;
    @NonFinal
    @Setter
    String presentationUrl;
}
