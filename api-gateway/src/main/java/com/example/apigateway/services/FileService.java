package com.example.apigateway.services;

import com.example.apigateway.client.file.FileClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;

    public void uploadFile(final MultipartFile file, Long userId) {
        fileClient.uploadFile(file, userId);
    }

    public String getFile(final String fileName) {
        return fileClient.getFileUrl(fileName);
    }

    public void getFileList() {
        fileClient.getFilesList();
    }

    public void deleteFile(final String fileName) {
        fileClient.deleteFile(fileName);
    }
}
