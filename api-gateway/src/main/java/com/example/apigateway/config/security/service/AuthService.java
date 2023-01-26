package com.example.apigateway.config.security.service;

import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    final com.example.apigateway.model.User user = userService.findUserByEmail(email);
    List<SimpleGrantedAuthority> list = user
            .getRoles()
            .stream()
            .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
            .toList();
    return new User(user.getEmail(), user.getPassword(), list);
  }
}
