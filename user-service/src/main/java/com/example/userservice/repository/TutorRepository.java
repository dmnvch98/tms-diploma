package com.example.userservice.repository;

import com.example.userservice.model.Tutor;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface TutorRepository extends Repository<Tutor, Long> {

    Tutor findAllByUserId(Long userId);

    @Transactional
    void delete(Tutor tutor);
}
