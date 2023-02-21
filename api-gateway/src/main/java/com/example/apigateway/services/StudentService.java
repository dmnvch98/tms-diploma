package com.example.apigateway.services;

import com.example.apigateway.client.user.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentClient studentClient;

    public void deleteStudent(Long userId) {
        studentClient.deleteStudent(userId);
    }
}
