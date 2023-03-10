package com.example.userservice.services;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

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

    public User findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    public Boolean existsByEmailAndPassword(String email, String password) {
        return repository.existsByEmailAndPassword(email, password);
    }

    public void updateRefreshToken(String token, Long userId) {
        repository.updateRefreshToken(token, userId);
    }

    public List<User> findTutorsWithExistingConvDetails() {
        return repository.findTutorsWithExistingConvDetails();
    }
    public int setAvatar(Long userId) {
        return repository.setAvatar(userId + userAvatarNamePostfix, userId);
    }

    public int deleteAvatar(Long userId) {
        return repository.deleteAvatar(userId);
    }
}
