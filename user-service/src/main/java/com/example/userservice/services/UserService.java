package com.example.userservice.services;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User get(Long id) {
        return repository.findUserById(id);
    }

    public Boolean isEmailExists(String email) {
        return repository.existsByEmail(email);
    }

    public void updateUser(User user) {
        if ( user.getFirstName() != null ) {
           repository.updateFirstName(user.getFirstName(), user.getId());
        }
        if ( user.getLastName() != null ) {
            repository.updateLastName(user.getLastName(), user.getId());
        }
        if ( user.getNationality() != null ) {
            repository.updateNationality(user.getNationality(), user.getId());
        }
        if ( user.getEmail() != null ) {
            repository.updateEmail(user.getEmail(), user.getId());
        }
    }
}
