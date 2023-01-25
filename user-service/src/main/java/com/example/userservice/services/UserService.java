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

    public User findUserByTutorId(Long tutorId) {
        return repository.findUserByTutorId(tutorId);
    }

    public User findUserByStudentId(Long studentId) {
        return repository.findUserByStudentId(studentId);
    }

}
