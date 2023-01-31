package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @DeleteMapping
    public ResponseEntity<?> deleteStudent(Authentication authentication) {
        return ResponseEntity.ok(studentService.deleteStudent(
            ((PrincipalUser) authentication.getPrincipal()).getUserId()));
    }
}
