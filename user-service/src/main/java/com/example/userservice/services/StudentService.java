package com.example.userservice.services;

import com.example.userservice.model.Student;
import com.example.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    public final StudentRepository studentRepository;

    public Student save(final Student student) {
        return studentRepository.save(student);
    }

    public boolean deleteByUserId(final Long userId) {
        return studentRepository.deleteByUserId(userId) == 1;
    }
}
