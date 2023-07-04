package com.example.apigateway.services;

import com.example.apigateway.client.user.UserClient;
import com.example.apigateway.client.file.FileClient;
import com.example.apigateway.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileClient fileClient;
    private final UserClient userClient;

    public ResponseDto uploadFile(final MultipartFile file, Long userId) {
        ResponseDto responseDto = fileClient.uploadAvatar(file, userId);
        return responseDto.getMessage() != null && userClient.setAvatar(userId) == 1
            ? responseDto
            : null;
    }


    public ResponseDto getAvatarUrl(final Long userId) {
        return fileClient.getAvatarUrl(userId);
    }

    public boolean deleteAvatar(final String fileName, Long userId) {
        boolean isDeleted = Boolean.TRUE.equals(fileClient.deleteAvatar(fileName).getBody());
        return isDeleted && userClient.deleteAvatar(userId) == 1;
    }

    public ResponseDto uploadStudentVideoPresentation(final MultipartFile file, final Long studentId)
        throws IOException {
        return fileClient.uploadStudentVideoPresentation(file, studentId);
    }

    public ResponseDto uploadTutorVideoPresentation(final MultipartFile file, Long tutorId) throws IOException{
        return fileClient.uploadTutorVideoPresentation(file, tutorId);
    }

    public ResponseDto getStudentVideoPresentationUrl(final Long studentId) {
        return fileClient.getStudentVideoPresentationUrl(studentId);
    }

    public ResponseDto getTutorVideoPresentationUrl(final Long tutorId) {
        return fileClient.getTutorVideoPresentationUrl(tutorId);
    }

    public ResponseEntity<Boolean> deleteStudentVideoPresentation(final Long studentId) {
        return fileClient.deleteStudentVideoPresentation(studentId);
    }

    public ResponseEntity<Boolean> deleteTutorVideoPresentation(final Long tutorId) {
        return fileClient.deleteTutorVideoPresentation(tutorId);
    }
}
