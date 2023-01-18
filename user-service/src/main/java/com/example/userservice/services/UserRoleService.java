package com.example.userservice.services;

import com.example.userservice.model.Student;
import com.example.userservice.model.Tutor;
import com.example.userservice.repository.StudentRepository;
import com.example.userservice.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
}
