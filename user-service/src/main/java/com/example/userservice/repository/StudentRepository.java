package com.example.userservice.repository;

import com.example.userservice.model.Student;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends Repository<Student, Long> {
    Student save(final Student student);

    @Modifying
    @Query("DELETE from students where user_id=:userId")
    int deleteByUserId(final @Param("userId") Long userId);
}
