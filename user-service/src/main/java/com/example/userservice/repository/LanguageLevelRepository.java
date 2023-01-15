package com.example.userservice.repository;

import com.example.userservice.model.LanguageLevel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface LanguageLevelRepository extends Repository<LanguageLevel, Long> {
    @Query("select ll.id from language_levels ll where level_id=:levelId and language_id=:languageId")
    Long getId(@Param("levelId") Long levelId, @Param("languageId") Long languageId);

}
