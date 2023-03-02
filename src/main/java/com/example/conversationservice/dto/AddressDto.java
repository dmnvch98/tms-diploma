package com.example.conversationservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressDto {
    String latitude;
    String longitude;
}
