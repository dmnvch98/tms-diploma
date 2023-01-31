package com.example.userservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class StudentDto {
    Long studentId;
    Long userId;
    String location;
    String aboutMe;
}
