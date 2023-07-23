package com.example.apigateway.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Data
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
    String refreshToken;
    String location;
    String avatarName;
}
