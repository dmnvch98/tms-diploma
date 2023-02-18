package com.example.apigateway.facades;

import com.example.apigateway.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;

    public String deleteTutor(Long userId) {
        return tutorService.deleteTutor(userId);
    }
}
