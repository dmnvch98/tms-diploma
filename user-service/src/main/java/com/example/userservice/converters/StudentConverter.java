package com.example.userservice.converters;

import com.example.userservice.dto.StudentDto;
import com.example.userservice.model.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface StudentConverter {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUserUpdate(StudentDto studentRequestDto, @MappingTarget Student.StudentBuilder student);

    StudentDto studentToRequestDto(Student student);

    Student dtoToStudent(StudentDto studentDto);
}
