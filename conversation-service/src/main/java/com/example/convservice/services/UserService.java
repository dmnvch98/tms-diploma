package com.example.convservice.services;

import com.example.convservice.client.TutorClient;
import com.example.convservice.dto.FilterTutorsRequestDto;
import com.example.convservice.dto.TutorCardInfo;
import com.example.convservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TutorClient userClient;

    public List<TutorCardInfo> findTutorsWithExistingConvDetails(Long lastTutorId) {
        return userClient.findTutorsWithExistingConvDetails(lastTutorId);
    }


    public List<TutorCardInfo> filterTutors(Long tutorId, FilterTutorsRequestDto dto) {
        return userClient.filterTutors(tutorId, dto);
    }
}
