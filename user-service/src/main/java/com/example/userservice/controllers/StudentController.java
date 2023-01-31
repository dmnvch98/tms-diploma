package com.example.userservice.controllers;

import com.example.userservice.facades.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentFacade studentFacade;

    @DeleteMapping("/{userId}")
    public String deleteStudent(@PathVariable("userId") Long userId) {
        return studentFacade.deleteStudent(userId);
    }
}
