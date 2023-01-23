package com.example.userservice.converters;

import com.example.userservice.dto.TutorDto;
import com.example.userservice.model.Tutor;
import org.mapstruct.Mapper;

@Mapper
public interface TutorConverter {

    TutorDto tutorToDto(Tutor tutor);

    Tutor dtoToTutor(TutorDto tutorDto);
}
