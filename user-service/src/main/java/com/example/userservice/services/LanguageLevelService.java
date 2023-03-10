package com.example.userservice.services;

import com.example.userservice.dto.LanguageLevelDto;
import com.example.userservice.model.Language;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.Level;
import com.example.userservice.model.UserLanguageLevel;
import com.example.userservice.repository.LanguageLevelRepository;
import com.example.userservice.repository.LanguageRepository;
import com.example.userservice.repository.LevelRepository;
import com.example.userservice.repository.UserLanguageLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageLevelService {
    private final LanguageLevelRepository languageLevelRepository;
    private final UserLanguageLevelRepository userLanguageLevelRepository;
    private final LanguageRepository languageRepository;
    private final LevelRepository levelRepository;

    public Long getLanguageLevelId(Long level, Long language) {
        return languageLevelRepository.getId(level, language);
    }

    public Long getLanguageLevelId(LanguageLevelDto languageLevelDto) {
        return languageLevelRepository.getId(languageLevelDto.getLevel().getLevelId(),
            languageLevelDto.getLanguage().getLanguageId());
    }

    public UserLanguageLevel saveUserLanguageLevel(UserLanguageLevel userLanguageLevel) {
        if (!isUserLanguageLevelExists(userLanguageLevel)) {
            return userLanguageLevelRepository.save(userLanguageLevel);
        }
        return userLanguageLevel;
    }

    public List<LanguageLevel> findLanguageLevelsByUserId(Long userId) {
        return languageLevelRepository.findLanguageLevelsByUserId(userId);
    }

    public List<Language> findAllLanguages() {
        return languageRepository.findAll();
    }

    public List<Level> findAllLevels() {
        return levelRepository.findAll();
    }

    public LanguageLevel userLanguageLevelToLl(UserLanguageLevel userLanguageLevel) {
        return languageLevelRepository.findAllByLanguageLevelId(userLanguageLevel.getLanguageLevelId());
    }

    public LanguageLevel languageLevelIdToLanguageLevel(Long languageLevelId) {
        return languageLevelRepository.findAllByLanguageLevelId(languageLevelId);
    }

    public void deleteUserLanguageLevel(Long languageLevelId, Long userId) {
        userLanguageLevelRepository.deleteByLanguageLevelIdAndUserId(languageLevelId, userId);
    }

    public Boolean isUserLanguageLevelExists(UserLanguageLevel userLanguageLevel) {
        return userLanguageLevelRepository.existsByUserIdAndLanguageLevelId(
                userLanguageLevel.getUserId(), userLanguageLevel.getLanguageLevelId());
    }
}
