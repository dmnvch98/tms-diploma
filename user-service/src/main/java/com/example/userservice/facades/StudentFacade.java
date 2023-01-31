package com.example.userservice.facades;

import com.example.userservice.exceptions.StudentCannotBeDeletedException;
import com.example.userservice.model.User;
import com.example.userservice.services.StudentService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {
    private final StudentService studentService;
    private final UserService userService;
    public String deleteStudent(Long userId) {
        User user = userService.get(userId);
        if (user.getTutor() != null) {
            studentService.deleteStudent(userId);
            return null;
        } else {
            throw new StudentCannotBeDeletedException();
        }
    }
}
