package com.example.apigateway.facades;

import com.example.apigateway.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;

    public void deleteTutor(Long userId) {
        tutorService.deleteTutor(userId);
    }
}
