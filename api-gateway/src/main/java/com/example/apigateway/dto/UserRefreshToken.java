package com.example.apigateway.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class UserRefreshToken {
    Long userId;
    String token;
}
