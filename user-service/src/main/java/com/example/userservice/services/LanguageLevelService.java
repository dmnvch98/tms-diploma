package com.example.userservice.services;

import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.repository.LanguageLevelRepository;
import com.example.userservice.repository.UserLanguageLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageLevelService {
    private final LanguageLevelRepository languageLevelRepository;
    private final UserLanguageLevelRepository userLanguageLevelRepository;

    public Long getLanguageLevelId(Long level, Long language) {
        return languageLevelRepository.getId(level, language);
    }

    public void saveUserLanguageLevel(UserLanguageLevel userLanguageLevel) {
        userLanguageLevelRepository.save(userLanguageLevel);
    }

    public List<LanguageLevel> findLanguageLevelsByUserId(Long userId) {
        return languageLevelRepository.findLanguageLevelsByUserId(userId);
    }
}
