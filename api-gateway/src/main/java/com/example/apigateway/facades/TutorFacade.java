package com.example.apigateway.facades;

import com.example.apigateway.model.Tutor;
import com.example.apigateway.model.User;
import com.example.apigateway.services.TutorService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;

    public void deleteTutor(Long userId) {
        tutorService.deleteTutor(userId);
    }

    public Tutor saveTutor(Tutor tutor) {
        return tutorService.save(tutor);
    }
}
