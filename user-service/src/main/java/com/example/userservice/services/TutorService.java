package com.example.userservice.services;

import com.example.userservice.model.Tutor;
import com.example.userservice.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorService {
    public final TutorRepository tutorRepository;

    public Tutor save(final Tutor tutor) {
        Tutor result = Tutor.builder().build();
        try {
            log.info("Saving a new tutor. Tutor: {}", tutor);
            result = tutorRepository.save(tutor);
        } catch (Exception e) {
            log.error("An error occurred when saving the tutor: {}", e.getMessage());
        }
        log.info("Tutor successfully saved. Tutor : {}", result);
        return result;
    }

    public boolean deleteByUserId(final Long userId) {
        return tutorRepository.deleteByUserId(userId) == 1;
    }
}
