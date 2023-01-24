package com.example.apigateway.controllers;

import com.example.apigateway.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(studentService.deleteStudent(userId));
    }
}
