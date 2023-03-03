package com.example.userservice.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressDto {
    String latitude;
    String longitude;
}
