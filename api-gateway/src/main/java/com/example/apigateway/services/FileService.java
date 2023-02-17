package com.example.apigateway.services;

import com.example.apigateway.client.UserClient;
import com.example.apigateway.client.file.FileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;
    private final UserClient userClient;

    public String uploadFile(final MultipartFile file, Long userId) {
        Optional<String> fileUrl = fileClient.uploadFile(file, userId);
        if (fileUrl.isPresent()) {
            if (userClient.setAvatar(userId) == 1) {
                return fileUrl.get();
            }
        }
        return null;
    }

    public String getFile(final String fileName) {
        return fileClient.getFileUrl(fileName);
    }

    public boolean deleteFile(final Long userId) {
        boolean isDeleted = Boolean.TRUE.equals(fileClient.deleteFile(userId + "_avatar.png").getBody());
        boolean ss = userClient.deleteAvatar(userId) == 1;
        return isDeleted && ss;
    }
}
