package com.example.apigateway.facades;

import com.example.apigateway.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {

    private final StudentService studentService;

    public void deleteStudent(Long userId) {
        studentService.deleteStudent(userId);
    }
}
