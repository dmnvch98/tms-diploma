package com.example.userservice.facades;

import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.exceptions.StudentCannotBeDeletedException;
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
    private final UserService userService;
    private final StudentService studentService;
    @Value("${roles.student_role_name}")
    String studentRoleName;

    public void deleteStudent(Long userId) {
        User user = userService.get(userId);
        if (user.getTutor() != null) {
            userService.deleteRoleFromUser(studentRoleName, userId);
            studentService.deleteByUserId(userId);
        } else {
            throw new StudentCannotBeDeletedException();
        }
    }

    public Student save(Student student) {
        userService.addRoleToUser(studentRoleName, student.getUserId());
        return studentService.save(student);
    }
}
