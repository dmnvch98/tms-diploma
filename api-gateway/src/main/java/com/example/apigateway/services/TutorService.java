package com.example.apigateway.services;

import com.example.apigateway.client.TutorClient;
import com.example.apigateway.dto.TutorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorClient tutorClient;

    public TutorDto saveTutor(TutorDto tutorDto) {
        return tutorClient.saveTutor(tutorDto);
    }

    public TutorDto updateTutor(TutorDto tutor) {
        return tutorClient.updateTutor(tutor);
    }

    public String deleteTutor(Long userId) {
        return tutorClient.deleteTutor(userId);
    }
}
