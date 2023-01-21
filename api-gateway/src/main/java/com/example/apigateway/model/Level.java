package com.example.apigateway.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
@Data
@Builder
@Jacksonized
public class Level {

    Long levelId;

    String description;
}
