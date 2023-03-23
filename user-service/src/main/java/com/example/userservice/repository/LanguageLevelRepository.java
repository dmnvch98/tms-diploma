package com.example.userservice.repository;

import com.example.userservice.model.Language;
import com.example.userservice.model.LanguageLevel;
import com.example.userservice.model.Level;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LanguageLevelRepository extends Repository<LanguageLevel, Long> {

    String FIND_LANGUAGE_LEVEL_SQL = "SELECT ll.language_level_id AS language_level_id, l.level_id AS level_level_id, l.description" +
        " AS level_description, lg.language_id AS language_language_id, lg.description AS language_description" +
        " FROM language_levels ll" +
        " LEFT OUTER JOIN levels l ON l.level_id = ll.level_id" +
        " LEFT OUTER JOIN languages lg ON lg.language_id = ll.language_id";

    @Query("select ll.language_level_id from language_levels ll where level_id=:levelId and language_id=:languageId")
    Long getId(@Param("levelId") Long levelId, @Param("languageId") Long languageId);

    @Query(FIND_LANGUAGE_LEVEL_SQL +
        " LEFT OUTER JOIN user_language_levels ull on ll.language_level_id = ull.language_level_id" +
        " WHERE ull.user_id=:userId")
    List<LanguageLevel> findLanguageLevelsByUserId(@Param("userId") Long userId);

    @Query(FIND_LANGUAGE_LEVEL_SQL + " WHERE language_level_id=:languageLevelId")
    LanguageLevel findAllByLanguageLevelId(@Param("languageLevelId") Long languageLevelId);

}
