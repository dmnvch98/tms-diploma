package com.example.userservice.facades;

import com.example.userservice.converters.StudentConverter;
import com.example.userservice.dto.StudentDto;
import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.services.StudentService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {
    private final StudentService studentService;
    private final UserService userService;
    @Value("${messages.delete-student-profile-error}")
    private String deleteStudentProfileError;

    public String deleteStudent(Long userId) {
        User user = userService.get(userId);
        if (user.getTutor() != null) {
            studentService.deleteStudent(userId);
            return null;
        } else {
            return deleteStudentProfileError;
        }
    }
}
