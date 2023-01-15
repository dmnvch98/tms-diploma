package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_language_levels")
@Value
@Builder
public class UserLanguageLevel {
    @Id
    Long id;
    @Column("user_id")
    Long user_id;

    @Column("language_level_id")
    Long languageLevelId;
}
