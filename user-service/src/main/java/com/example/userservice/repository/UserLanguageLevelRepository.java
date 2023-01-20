package com.example.userservice.repository;

import com.example.userservice.model.UserLanguageLevel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserLanguageLevelRepository extends Repository<UserLanguageLevel, Long> {
    UserLanguageLevel save(UserLanguageLevel userLanguageLevel);

    @Modifying
    @Query("DELETE FROM user_language_levels WHERE language_level_id=:languageLevelId AND user_id=:userId")
    void deleteByLanguageLevelIdAndUserId(@Param("languageLevelId") Long languageLevelId, @Param("userId") Long userId);

}
