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
        return tutorRepository.save(tutor);
    }
}
