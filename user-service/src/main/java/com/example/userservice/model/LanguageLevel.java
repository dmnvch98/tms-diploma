package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("language_levels")
@Value
@Builder
public class LanguageLevel {
    @Id
    Long id;
    @Column("language_id")
    Long languageId;

    @Column("level_id")
    Long levelId;
}