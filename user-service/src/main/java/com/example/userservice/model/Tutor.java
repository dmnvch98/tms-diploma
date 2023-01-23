package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tutors")
@Value
@Builder
public class Tutor {
    @Id
    Long tutorId;
    @Column("user_id")
    Long userId;

    @Column("location")
    String location;
    @Column("about_me")
    String aboutMe;
}
