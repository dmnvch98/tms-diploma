package com.example.convservice.services;

import com.example.convservice.client.TutorClient;
import com.example.convservice.dto.TutorCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final TutorClient userClient;

    public List<TutorCardInfo> findTutorsWithExistingConvDetails() {
        return userClient.findTutorsWithExistingConvDetails();
    }
}
