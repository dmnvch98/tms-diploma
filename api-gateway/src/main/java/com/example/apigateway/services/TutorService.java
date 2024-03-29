package com.example.apigateway.services;

import com.example.apigateway.client.user.TutorClient;
import com.example.apigateway.dto.FilterTutorsRequestDto;
import com.example.apigateway.dto.TutorShortUserInfoDto;
import com.example.apigateway.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TutorShortUserInfoDto> findTutorsWhoHaveNotBookedConvDetails(Long lastTutorId) {
        return tutorClient.findTutorsWhoHaveNotBookedConvDetails(lastTutorId);
    }

    public List<TutorShortUserInfoDto> filterTutors(Long tutorId, FilterTutorsRequestDto dto) {
        return tutorClient.filterTutorsWhoHaveNotBookedConvDetails(tutorId, dto);
    }

    public Tutor update(Tutor tutor) {
        return tutorClient.update(tutor);
    }
}
