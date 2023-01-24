package com.example.apigateway.services;

import com.example.apigateway.client.TutorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorClient tutorClient;
    public String deleteTutor(Long userId) {
        return tutorClient.deleteTutor(userId);
    }
}
