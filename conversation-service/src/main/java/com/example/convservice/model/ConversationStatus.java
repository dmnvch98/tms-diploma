package com.example.convservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table("conv_statuses")
public class ConversationStatus {
    @Id
    Long convStatusId;
    @Column("description")
    String description;
}
