package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("language_levels")
@Value
@Builder
public class LanguageLevel {
    @Id
    Long languageLevelId;
    @MappedCollection(idColumn = "language_id")
    Language language;

    @MappedCollection(idColumn = "level_id")
    Level level;
}