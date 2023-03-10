package com.example.apigateway.model;

import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
public class User {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    String nationality;
    String gender;
    List<String> roles;
    @NonFinal
    @Setter
    String refreshToken;
    String location;
    @NonFinal
    @Setter
    String avatarName;
}
