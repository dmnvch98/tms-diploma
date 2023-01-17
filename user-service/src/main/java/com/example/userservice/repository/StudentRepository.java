package com.example.userservice.repository;

import com.example.userservice.model.Student;
import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long> {
    Student save(Student student);
}
