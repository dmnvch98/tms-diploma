package com.example.convservice.services;

import com.example.convservice.client.FileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;

    public String getAvatarUrl(String fileName) {
        return fileClient.getAvatarUrl(fileName).getMessage();
    }
}
