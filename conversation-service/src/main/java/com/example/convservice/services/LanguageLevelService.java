package com.example.convservice.services;

import com.example.convservice.client.LanguageLevelClient;
import com.example.convservice.converters.utils.FindLanguageLevelById;
import com.example.convservice.converters.utils.FindLanguageLevelId;
import com.example.convservice.dto.LanguageLevelDto;
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
