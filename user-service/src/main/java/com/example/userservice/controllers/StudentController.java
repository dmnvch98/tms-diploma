package com.example.userservice.controllers;

import com.example.userservice.dto.StudentDto;
import com.example.userservice.facades.StudentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentFacade studentFacade;

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentRequestDto) {
        return studentFacade.updateStudent(studentRequestDto);
    }

    @PostMapping
    public StudentDto saveStudent(@RequestBody StudentDto studentDto) {
        return studentFacade.saveStudent(studentDto);
    }

    @DeleteMapping("/{userId}")
    public String deleteStudent(@PathVariable("userId") Long userId) {
        return studentFacade.deleteStudent(userId);
    }
}
