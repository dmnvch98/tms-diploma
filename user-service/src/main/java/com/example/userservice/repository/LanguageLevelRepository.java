package com.example.userservice.repository;

import com.example.userservice.model.LanguageLevel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LanguageLevelRepository extends Repository<LanguageLevel, Long> {
    @Query("select ll.id from language_levels ll where level_id=:levelId and language_id=:languageId")
    Long getId(@Param("levelId") Long levelId, @Param("languageId") Long languageId);

    @Query("select * from language_levels ll " +
            "join user_language_levels ull on ll.id = ull.language_level_id " +
            "where user_id=:userId")
    List<LanguageLevel> findLanguageLevelsByUserId(@Param("userId") Long userId);

}
