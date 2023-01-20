package com.example.userservice.repository;

import com.example.userservice.model.UserLanguageLevel;
import org.springframework.data.repository.Repository;

public interface UserLanguageLevelRepository extends Repository<UserLanguageLevel, Long> {
    UserLanguageLevel save(UserLanguageLevel userLanguageLevel);

    void deleteById(Long id);
}
