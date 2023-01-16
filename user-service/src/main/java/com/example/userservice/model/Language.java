package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("languages")
@Value
@Builder
@Jacksonized
public class Language {
    @Id
    Long languageId;

    @Column("description")
    String description;
}
