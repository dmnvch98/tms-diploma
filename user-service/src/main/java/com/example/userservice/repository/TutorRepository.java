package com.example.userservice.repository;

import com.example.userservice.model.Tutor;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface TutorRepository extends Repository<Tutor, Long> {
    Tutor save(Tutor tutor);

    Tutor findAllByUserId(Long userId);

    @Modifying
    @Query("DELETE from tutors where user_id=:userId")
    void deleteByUserId(@Param("userId") Long userId);
}
