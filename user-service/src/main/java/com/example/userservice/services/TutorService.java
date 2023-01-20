package com.example.userservice.services;

import com.example.userservice.model.Tutor;
import com.example.userservice.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void updateTutor(Tutor tutor) {
        if (tutor.getAboutMe() != null) {
            tutorRepository.updateAboutMe(tutor.getAboutMe(), tutor.getUserId());
        }
        if (tutor.getLocation() != null) {
            tutorRepository.updateLocation(tutor.getLocation(), tutor.getUserId());
        }
    }

    public Tutor getTutor(Long userId) {
        return tutorRepository.findAllByUserId(userId);
    }

    public void deleteTutor(Long userId) {
        tutorRepository.deleteByUserId(userId);
    }
}
