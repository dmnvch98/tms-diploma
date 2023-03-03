package com.example.userservice.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

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
    @NonFinal
    List<String> roles;
    @MappedCollection(idColumn = "user_id")
    @NonFinal
    @Setter
    Student student;
    @MappedCollection(idColumn = "user_id")
    @NonFinal
    @Setter
    Tutor tutor;
    @Column("location")
    String location;
    @Column("refresh_token")
    String refreshToken;
    @Column("avatar_name")
    String avatarName;
}
