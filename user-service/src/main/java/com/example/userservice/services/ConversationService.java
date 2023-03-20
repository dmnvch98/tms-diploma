package com.example.userservice.services;

import com.example.userservice.client.conversation.ConversationsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationService {
    private final ConversationsClient conversationClient;

    public Integer countStudentLessons(Long studentId) {
        return conversationClient.countStudentLessons(studentId);
    }

    public Integer countTutorLessons(Long tutorId) {
        return conversationClient.countTutorLessons(tutorId);
    }
}
