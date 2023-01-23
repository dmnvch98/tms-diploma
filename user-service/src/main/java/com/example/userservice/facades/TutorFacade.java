package com.example.userservice.facades;

import com.example.userservice.converters.TutorConverter;
import com.example.userservice.dto.TutorDto;
import com.example.userservice.model.Tutor;
import com.example.userservice.model.User;
import com.example.userservice.services.TutorService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;
    private final TutorConverter tutorConverter;
    private final UserService userService;
    @Value("${messages.delete-tutor-profile-error}")
    private String deleteTutorProfileError;

    public TutorDto updateTutor(TutorDto tutorDto) {
        Tutor tutor = tutorConverter.dtoToTutor(tutorDto);
        tutorService.updateTutor(tutor);
        tutor = tutorService.getTutor(tutor.getUserId());
        return tutorConverter.tutorToDto(tutor);
    }

    public TutorDto saveTutor(TutorDto tutorDto) {
        Tutor tutor = tutorConverter.dtoToTutor(tutorDto);
        return tutorConverter.tutorToDto(tutorService.saveTutor(tutor));
    }

    public String deleteTutor(Long userId) {
        User user = userService.get(userId);
        if (user.getStudent() != null) {
            tutorService.deleteTutor(userId);
            return null;
        } else {
            return deleteTutorProfileError;
        }
    }
}
