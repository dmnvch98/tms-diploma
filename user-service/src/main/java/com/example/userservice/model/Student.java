package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("students")
@Value
@Builder
@Jacksonized
public class Student {
    @Id
    Long studentId;
    @Column("user_id")
    Long userId;

    @Column("location")
    String location;
    @Column("about_me")
    String aboutMe;
}
