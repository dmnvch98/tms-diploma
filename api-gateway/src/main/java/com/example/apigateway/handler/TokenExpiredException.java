package com.example.apigateway.handler;

import lombok.Builder;

@Builder
public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
    }
}
