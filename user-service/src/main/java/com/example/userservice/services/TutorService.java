package com.example.userservice.services;

import com.example.userservice.model.Tutor;
import com.example.userservice.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    public void deleteTutor(Long userId) {
        tutorRepository.delete(findTutorByUserId(userId));
    }

    public Tutor findTutorByUserId(Long userId) {
        return tutorRepository.findAllByUserId(userId);
    }

}
