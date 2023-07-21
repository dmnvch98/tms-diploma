package com.example.fileloader.facade;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.example.fileloader.utils.FileUtils.*;


@Component
@RequiredArgsConstructor
public class FileFacade {
    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    public String tutorsVideoPresentationStorageName;
    @Value("${aws.students_video_presentation_storage_name}")
    public String studentsVideoPresentationStorageName;
    @Value("${avatar.default}")
    public String defaultAvatarName;
    private final FileService fileService;


    public ResponseDto uploadAvatar(InputStream inputStream, Long userId) throws IOException {
        String fileName = getAvatarName(userId);
        String storageName = avatarStorageName;

        String fileUrl = fileService.uploadFile(inputStream, fileName, storageName).orElse(null);

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();
    }


    public ResponseDto uploadTutorVideoPresentation(InputStream inputStream, Long tutorId) throws IOException {
        String fileName = getTutorVideoPresentationName(tutorId);
        String storageName = tutorsVideoPresentationStorageName;

        String fileUrl = fileService.uploadFile(inputStream, fileName, storageName).orElse(null);

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();
    }

    public ResponseDto uploadStudentVideoPresentation(InputStream inputStream, Long studentId) throws IOException {
        String fileName = getStudentVideoPresentationName(studentId);
        String storageName = studentsVideoPresentationStorageName;

        String fileUrl = fileService.uploadFile(inputStream, fileName, storageName).orElse(null);

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .build();
    }

    public ResponseDto uploadDefaultAvatar(InputStream inputStream) throws IOException {
        String fileName = defaultAvatarName;
        String storageName = avatarStorageName;

        String fileUrl = fileService.uploadFile(inputStream, fileName, storageName).orElse(null);

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();
    }

    public List<String> getFilesList(String storageName) {
        return fileService.getFilesList(storageName);
    }

    public ResponseDto getAvatarUrl(Long userId) {
        String fileName = getAvatarName(userId);
        String storageName = avatarStorageName;

        String fileUrl = fileService.getFileUrl(fileName, storageName, false)
            .orElse(null);

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .build();
    }

    public ResponseDto getStudentVideoPresentationUrl(Long studentId) {
        String fileName = getStudentVideoPresentationName(studentId);
        String storageName = studentsVideoPresentationStorageName;

        String fileUrl = fileService.getFileUrl(fileName, storageName, true).orElse("");

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .build();
    }

    public ResponseDto getTutorVideoPresentationUrl(Long tutorId) {
        String fileName = getTutorVideoPresentationName(tutorId);
        String storageName = tutorsVideoPresentationStorageName;

        String fileUrl = fileService.getFileUrl(fileName, storageName, true).orElse("");

        return ResponseDto.builder()
            .fileUrl(fileUrl)
            .build();
    }

    public ResponseEntity<Boolean> deleteStudentVideoPresentation(Long studentId) {
        String fileName = getStudentVideoPresentationName(studentId);
        String storageName = studentsVideoPresentationStorageName;

        Boolean isDeleted = fileService.deleteFile(fileName, storageName);

        return ResponseEntity.ok(isDeleted);
    }

    public ResponseEntity<Boolean> deleteTutorVideoPresentation(Long tutorId) {
        String fileName = getTutorVideoPresentationName(tutorId);
        String storageName = tutorsVideoPresentationStorageName;

        Boolean isDeleted = fileService.deleteFile(fileName, storageName);

        return ResponseEntity.ok(isDeleted);
    }

    public boolean deleteAvatar(Long userId) {
        String fileName = getAvatarName(userId);
        String storageName = avatarStorageName;

        return fileService.deleteFile(fileName, storageName);
    }

    public ResponseDto getDefaultAvatar() {
        return ResponseDto.builder()
            .fileUrl(fileService.getDefaultAvatarUrl(defaultAvatarName, avatarStorageName))
            .build();
    }

}
