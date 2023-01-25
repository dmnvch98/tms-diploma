package com.example.userservice.repository;

import com.example.userservice.model.Tutor;
import com.example.userservice.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends Repository<User, Long> {

    @Transactional
    User save(final User user);

    User findUserById(Long id);

    Boolean existsByEmail(String email);

    @Query("SELECT users.id AS id, users.email AS email, users.roles AS roles, users.gender AS gender, " +
            "users.password AS password, users.location AS location, users.last_name AS last_name, users.first_name " +
            "AS first_name, users.nationality AS nationality, tutor.user_id AS tutor_user_id, tutor.about_me " +
            "AS tutor_about_me, tutor.tutor_id AS tutor_tutor_id FROM users " +
            "LEFT OUTER JOIN tutors tutor " +
            "ON tutor.user_id = users.id WHERE tutor.tutor_id=:tutorId")
    User findUserByTutorId(@Param("tutorId") Long tutorId);

    @Query("SELECT users.id AS id, users.email AS email, users.roles AS roles, users.gender AS gender, " +
            "users.password AS password, users.location AS location, users.last_name AS last_name, users.first_name " +
            "AS first_name, users.nationality AS nationality, student.user_id AS student_user_id, student.about_me " +
            "AS student_about_me, student.student_id AS student_student_id FROM users " +
            "LEFT OUTER JOIN students student " +
            "ON student.user_id = users.id WHERE student.student_id=:studentId")
    User findUserByStudentId(@Param("studentId") Long studentId);
}
