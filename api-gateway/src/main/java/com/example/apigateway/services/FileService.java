package com.example.apigateway.services;

import com.example.apigateway.client.user.UserClient;
import com.example.apigateway.client.file.FileClient;
import com.example.apigateway.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;
    private final UserClient userClient;

    public ResponseDto uploadFile(final MultipartFile file, Long userId) {
        ResponseDto responseDto = fileClient.uploadFile(file, userId);
        return responseDto.getMessage() != null && userClient.setAvatar(userId) == 1
            ? responseDto
            : null;
    }

    public ResponseDto getFile(final String fileName) {
        return fileClient.getFileUrl(fileName);
    }

    public boolean deleteFile(final String fileName, Long userId) {
        boolean isDeleted = Boolean.TRUE.equals(fileClient.deleteFile(fileName).getBody());
        return isDeleted && userClient.deleteAvatar(userId) == 1;
    }
}
