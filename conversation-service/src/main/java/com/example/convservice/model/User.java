package com.example.convservice.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class User {

    Long id;

    String firstName;

    String lastName;

    String email;

    String password;

    Long nationality;

    String gender;
    List<String> roles;
    String location;
    String refreshToken;
}
