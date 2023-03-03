package com.example.convservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("levels")
@Value
@Builder
@Jacksonized
public class Level {
    @Id
    Long levelId;

    @Column("description")
    String description;
}
