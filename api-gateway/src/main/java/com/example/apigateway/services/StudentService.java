package com.example.apigateway.services;

import com.example.apigateway.client.user.StudentClient;
import com.example.apigateway.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentClient studentClient;

    public void deleteStudent(Long userId) {
        studentClient.deleteStudent(userId);
    }

    public Student save(Student student) {
        return studentClient.save(student);
    }

    public Student update(Student student) {
        return studentClient.update(student);
    }
}
