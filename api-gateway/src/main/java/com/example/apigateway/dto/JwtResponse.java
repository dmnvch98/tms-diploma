package com.example.apigateway.dto;

import lombok.Value;

@Value
public class JwtResponse {
    String accessToken;
    String refreshToken;
}
