package com.example.userservice.services;

import com.example.userservice.model.Student;
import com.example.userservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void updateStudent(Student student) {
        if (student.getAboutMe() != null) {
            studentRepository.updateAboutMe(student.getAboutMe(), student.getUserId());
        }
        if (student.getLocation() != null) {
            studentRepository.updateLocation(student.getLocation(), student.getUserId());
        }
    }

    public Student getStudent(Long userId) {
        return studentRepository.findAllByUserId(userId);
    }

    public void deleteStudent(Long userId) {
        studentRepository.deleteByUserId(userId);
    }
}
