package com.example.convservice.services;

import com.example.convservice.model.Conversation;
import com.example.convservice.repositories.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public List<Conversation> findAllByStudentId(Long studentId) {
        return conversationRepository.findAllByStudentId(studentId);
    }

    public List<Conversation> findAllByTutorId(Long tutorId) {
        return conversationRepository.findAllByTutorId(tutorId);
    }
}
