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

    public Tutor getTutor(Long userId) {
        return tutorRepository.findAllByUserId(userId);
    }

    public void deleteTutor(Long userId) {
        tutorRepository.deleteByUserId(userId);
    }
}
