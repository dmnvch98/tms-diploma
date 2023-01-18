package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("countries")
@Value
@Builder
@Jacksonized
public class Country {
    @Id
    Long countryId;

    @Column("description")
    String description;
}
