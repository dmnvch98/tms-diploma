package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    User save(final User user);

    User findUserById(Long id);

    Boolean existsByEmail(String email);
}
