package com.example.conversationservice.services;

import com.example.conversationservice.client.LanguageLevelClient;
import com.example.conversationservice.converters.utils.FindLanguageLevelById;
import com.example.conversationservice.converters.utils.FindLanguageLevelId;
import com.example.conversationservice.dto.LanguageLevelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageLevelService implements FindLanguageLevelById, FindLanguageLevelId {
    private final LanguageLevelClient client;

    public LanguageLevelDto findLanguageLevelById(Long id) {
        return client.findLanguageLevelById(id);
    }

    public Long findLanguageLevelId(LanguageLevelDto languageLevelDto) {
        return client.findLanguageLevelId(languageLevelDto);
    }
}
