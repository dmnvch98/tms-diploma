package com.example.userservice.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
@Builder
@Value
@Jacksonized
public class User {
    @Id
    Long id;
    @Column("first_name")
    String firstName;
    @Column("last_name")
    String lastName;
    @Column("email")
    String email;
    @Column("password")
    String password;
    @Column("nationality")
    Long nationality;
    @Column("gender")
    String gender;
    @Column("roles")
    String roles;
    @MappedCollection(idColumn = "user_id")
    Student student;
    @MappedCollection(idColumn = "user_id")
    Tutor tutor;

    String location;
}
