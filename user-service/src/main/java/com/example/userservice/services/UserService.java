package com.example.userservice.services;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    @Value("${find-tutor-page-size}")
    public int findTutorPageSize;

    public User save(User user) {
        return repository.save(user);
    }

//    public void save(User user) {
//        repository.save(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getNationality(),
//            user.getLocation(), user.getRoles().toArray(new String[0]), user.getGender());
//    }

    public User update(User user) {
        repository.update(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getLocation()
        );
        return get(user.getId());
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
        log.info("Updating refresh token for user. UserId : {}, Refresh token: {}", userId, token);
        repository.updateRefreshToken(token, userId);
        log.info("Refresh token is updated. serId : {}, Refresh token: {}", userId, token);
    }

    public List<User> findTutorsWhoHaveNotBookedConvDetails(Long lastTutorId) {
        return repository.findTutorsWhoHaveNotBookedConvDetails(lastTutorId, findTutorPageSize);
    }

    public int setAvatar(Long userId) {
        return repository.setAvatar(userId + userAvatarNamePostfix, userId);
    }

    public int deleteAvatar(Long userId) {
        return repository.deleteAvatar(userId);
    }

    public List<User> filterTutorsWhoHaveNotBookedConvDetails(Long lastTutorId, Double minPrice, Double maxPrice,
                                                              String city, Long countryId, Long convTypeId,
                                                              Long minLevel, Long languageId) {
        return repository.filterTutorsWhoHaveNotBookedConvDetails(lastTutorId, findTutorPageSize, minPrice,
            maxPrice, countryId, city, convTypeId, minLevel, languageId);
    }

    public boolean addRoleToUser(String role, Long id) {
        return repository.addRoleToUser(role, id) == 1;
    }

    public boolean deleteRoleFromUser(String role, Long id) {
        return repository.deleteRoleFromUser(role, id) == 1;
    }
}
