package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table("conversation_types")
public class ConversationType {
    @Id
    Long id;
    @Column("description")
    String description;
}
