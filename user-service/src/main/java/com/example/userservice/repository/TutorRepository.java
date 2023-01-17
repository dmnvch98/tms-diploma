package com.example.userservice.repository;

import com.example.userservice.model.Tutor;
import org.springframework.data.repository.Repository;

public interface TutorRepository extends Repository<Tutor, Long> {
    Tutor save(Tutor tutor);
}
