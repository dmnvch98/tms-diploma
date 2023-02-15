package com.example.apigateway.services;

import com.example.apigateway.client.file.FileClient;
import com.example.apigateway.dto.StorageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;
    public void createStorage(final StorageDto storage) {
        fileClient.createStorage(storage);
    }

    public void uploadFile(final MultipartFile file, final String storageName) throws IOException {
        fileClient.uploadFile(file, storageName);
    }
}
