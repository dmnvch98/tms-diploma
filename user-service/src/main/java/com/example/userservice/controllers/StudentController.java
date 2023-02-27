package com.example.userservice.controllers;

import com.example.userservice.facades.StudentFacade;
import com.example.userservice.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentFacade studentFacade;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId) {
        studentFacade.deleteStudent(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Student save(@RequestBody Student student) {
        return studentFacade.save(student);
    }
}
