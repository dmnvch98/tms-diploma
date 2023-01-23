package com.example.userservice.converters;

import com.example.userservice.dto.StudentDto;
import com.example.userservice.model.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentConverter {
    StudentDto studentToRequestDto(Student student);

    Student dtoToStudent(StudentDto studentDto);
}
