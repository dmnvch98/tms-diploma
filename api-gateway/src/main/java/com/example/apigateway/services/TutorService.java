package com.example.apigateway.services;

import com.example.apigateway.client.user.TutorClient;
import com.example.apigateway.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorClient tutorClient;

    public void deleteTutor(Long userId) {
        tutorClient.deleteTutor(userId);
    }

    public Tutor save(Tutor tutor) {
        return tutorClient.save(tutor);
    }
}
