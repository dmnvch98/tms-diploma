package com.example.convservice.services;

import com.example.convservice.client.TutorClient;
import com.example.convservice.dto.FilterTutorsRequestDto;
import com.example.convservice.dto.TutorShortUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TutorClient userClient;

    public List<TutorShortUserInfoDto> findTutorsWithExistingConvDetails(Long lastTutorId) {
        return userClient.findTutorsWithExistingConvDetails(lastTutorId);
    }


    public List<TutorShortUserInfoDto> filterTutors(Long tutorId, FilterTutorsRequestDto dto) {
        return userClient.filterTutors(tutorId, dto);
    }
}
