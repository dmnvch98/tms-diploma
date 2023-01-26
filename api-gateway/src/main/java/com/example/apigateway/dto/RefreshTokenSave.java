package com.example.apigateway.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class RefreshTokenSave {
    Long userId;
    String token;
}
