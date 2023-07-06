package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.fileloader.interfaces.FileInformationService;
import com.example.fileloader.interfaces.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final AmazonS3 amazonS3;
    private final FileInformationService fileInformationService;

    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    public String tutorsVideoPresentationStorageName;
    @Value("${aws.students_video_presentation_storage_name}")
    public String studentsVideoPresentationStorageName;
    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Override
    public String uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        log.info("Uploading file {}", fileName);
        try (inputStream) {
            amazonS3.putObject(
                storageName,
                fileName,
                inputStream,
                metadata);
            log.info("{} successfully uploaded", fileName);
            return fileInformationService.getFileUrl(fileName, storageName).orElse("");
        } catch (RuntimeException e) {
            log.error("An error occurred while uploading {}: {}", fileName, e);
        }
        return "";
    }

    @Override
    public String uploadTutorVideoPresentation(InputStream inputStream, Long tutorId) throws IOException {
        return uploadFile(
            inputStream,
            fileInformationService.getTutorVideoPresentationName(tutorId),
            tutorsVideoPresentationStorageName
        );
    }

    @Override
    public String uploadStudentVideoPresentation(InputStream inputStream, Long studentId) throws IOException {
        return uploadFile(
            inputStream,
            fileInformationService.getStudentVideoPresentationName(studentId),
            studentsVideoPresentationStorageName
        );
    }

    @Override
    public String uploadAvatar(InputStream inputStream, Long userId) throws IOException {
        String fileName = fileInformationService.getAvatarName(userId);
        return uploadFile(inputStream, fileName, avatarStorageName);
    }

    @Override
    public String uploadDefaultAvatar(InputStream file) throws IOException {
        return uploadFile(file, defaultAvatarName, avatarStorageName);
    }
}
