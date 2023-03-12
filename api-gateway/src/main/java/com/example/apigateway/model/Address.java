package com.example.apigateway.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Jacksonized
public class Address {
    Long addressId;
    String address;
    String city;
    String latitude;
    String longitude;
    @Setter
    @NonFinal
    Long tutorId;

}
