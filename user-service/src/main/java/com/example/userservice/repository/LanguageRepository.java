package com.example.userservice.repository;

import com.example.userservice.model.Language;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface LanguageRepository extends Repository<Language, Long> {
    List<Language> findAll();

    Language findAllByLanguageId(Long languageId);
}
