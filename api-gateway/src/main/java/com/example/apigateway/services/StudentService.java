package com.example.apigateway.services;

import com.example.apigateway.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentClient studentClient;

    public String deleteStudent(Long userId) {
        return studentClient.deleteStudent(userId);
    }
}
