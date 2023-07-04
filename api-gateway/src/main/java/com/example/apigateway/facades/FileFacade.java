package com.example.apigateway.facades;

import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.services.FileService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FileFacade {
    private final FileService fileService;
    private final UserService userService;

    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public ResponseDto uploadFile(final MultipartFile file, final Long userId) {
        return fileService.uploadFile(file, userId);
    }

    public ResponseDto getFile(Long userId) {
        return fileService.getAvatarUrl(userId);
    }

    public Boolean deleteAvatar(Long userId) {
        return fileService.deleteAvatar(userId + userAvatarNamePostfix, userId);
    }

    public ResponseDto uploadStudentVideoPresentation(final MultipartFile file, final Long userId)
        throws IOException {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return fileService.uploadStudentVideoPresentation(file, studentId);
    }

    public ResponseDto uploadTutorVideoPresentation(final MultipartFile file, Long userId) throws IOException {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return fileService.uploadTutorVideoPresentation(file, tutorId);
    }

    public ResponseDto getStudentVideoPresentationUrl(final Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return fileService.getStudentVideoPresentationUrl(studentId);
    }

    public ResponseDto getTutorVideoPresentationUrl(final Long tutorId) {
        return fileService.getTutorVideoPresentationUrl(tutorId);
    }

    public ResponseEntity<Boolean> deleteStudentVideoPresentation(final Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return fileService.deleteStudentVideoPresentation(studentId);
    }

    public ResponseEntity<Boolean> deleteTutorVideoPresentation(final Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return fileService.deleteTutorVideoPresentation(tutorId);
    }

    
}
