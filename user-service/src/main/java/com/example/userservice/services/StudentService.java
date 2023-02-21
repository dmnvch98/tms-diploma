package com.example.userservice.services;

import com.example.userservice.model.Student;
import com.example.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void deleteStudent(Long userId) {
        studentRepository.deleteByUserId(userId);
    }
}
