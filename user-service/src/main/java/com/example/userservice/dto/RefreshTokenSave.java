package com.example.userservice.dto;

import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class RefreshTokenSave {
    Long userId;
    String token;
}
