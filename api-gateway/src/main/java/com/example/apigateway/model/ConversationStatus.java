package com.example.apigateway.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Jacksonized
public class ConversationStatus {

    Long convStatusId;
    String description;
}
