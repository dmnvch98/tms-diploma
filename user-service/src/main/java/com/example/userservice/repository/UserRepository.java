package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends Repository<User, Long> {

    @Transactional
    User save(final User user);

    User findUserById(Long id);

    Boolean existsByEmail(String email);

}
