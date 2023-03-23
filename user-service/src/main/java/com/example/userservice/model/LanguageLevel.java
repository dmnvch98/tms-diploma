package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("language_levels")
@Value
@Builder
public class LanguageLevel {
    @Id
    Long languageLevelId;
    @Column("language_id")
    Language language;
    @Column("level_id")
    Level level;
}
