package com.example.userservice.repository;

import com.example.userservice.model.Student;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends Repository<Student, Long> {
    Student save(Student student);

    @Modifying
    @Query("update students set about_me=:aboutMe where user_id=:userId")
    void updateAboutMe(@Param("aboutMe") String aboutMe, @Param("userId") Long userId);

    @Modifying
    @Query("update students set location=:location where user_id=:userId")
    void updateLocation(@Param("location") String location, @Param("userId") Long userId);

    Student findAllByUserId(Long userId);

    @Modifying
    @Query("DELETE from students where user_id=:userId")
    void deleteByUserId(@Param("userId") Long userId);
}
