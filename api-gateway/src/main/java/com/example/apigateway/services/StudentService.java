package com.example.apigateway.services;

import com.example.apigateway.client.StudentClient;
import com.example.apigateway.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentClient studentClient;

    public StudentDto saveStudent(StudentDto studentDto) {
        return studentClient.saveStudent(studentDto);
    }

    public StudentDto updateStudent(StudentDto studentDto) {
        return studentClient.updateStudent(studentDto);
    }

    public String deleteStudent(Long userId) {
        return studentClient.deleteStudent(userId);
    }
}
