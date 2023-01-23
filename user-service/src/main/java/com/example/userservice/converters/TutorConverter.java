package com.example.userservice.converters;

import com.example.userservice.dto.TutorDto;
import com.example.userservice.model.Tutor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface TutorConverter {

    TutorDto tutorToDto(Tutor tutor);

    Tutor dtoToTutor(TutorDto tutorDto);
}