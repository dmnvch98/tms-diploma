package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("levels")
@Value
@Builder
public class Level {
    @Id
    Long levelId;

    @Column("description")
    String description;
}
