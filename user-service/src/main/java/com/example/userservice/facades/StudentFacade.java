package com.example.userservice.facades;

import com.example.userservice.exceptions.StudentCannotBeDeletedException;
import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {
    private final UserService userService;
    public void deleteStudent(Long userId) {
        User user = userService.get(userId);
        if (user.getTutor() != null) {
            user.getRoles().remove("Student");
            user.setStudent(null);
            userService.save(user);
        } else {
            throw new StudentCannotBeDeletedException();
        }
    }

    public Student save(Student student) {
        User user = userService.get(student.getUserId());
        user.setStudent(student);
        user.getRoles().add("Student");
        return userService.save(user).getStudent();
    }
}
