package com.example.userservice.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Value
@Builder
public class RefreshTokenSave {
    Long userId;
    String token;
}
