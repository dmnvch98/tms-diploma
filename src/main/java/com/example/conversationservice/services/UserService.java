package com.example.conversationservice.services;

import com.example.conversationservice.client.UserClient;
import com.example.conversationservice.dto.TutorCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public List<TutorCardInfo> findTutorsWithExistingConvDetails() {
        return userClient.findTutorsWithExistingConvDetails();
    }
}
