package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;

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
            return getFileUrl(fileName, storageName).orElse("");
        } catch (RuntimeException e) {
            log.error("An error occurred while uploading {}: {}", fileName, e);
        }
        return "";
    }


    @Override
    public List<String> getFilesList(String storageName) {
        log.info("Getting list of all files");
        List<String> filesList = new ArrayList<>();
        try {
            filesList = amazonS3.listObjects(storageName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
            log.info("All files are successfully loaded. The list size is {}", filesList.size());
        } catch (Exception e) {
            log.error("An error occurred while loading all files: " + e);
        }
        return filesList;
    }

    @Override
    public Boolean deleteFile(String fileName, String storageName) {
        try {
            log.info("Deleting file: {}", fileName);
            amazonS3.deleteObject(storageName, fileName);
            log.info("File {} successfully deleted", fileName);
            return true;
        } catch (Exception e) {
            log.error("Error during removing the file: " + e);
        }
        return false;
    }

    private String generateUrl(String fileName, String storageName) {
        log.info("Generating pre-signed url for {}", fileName);
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(accessExpirationInstant);
        String preSignedUrl = amazonS3
            .generatePresignedUrl(storageName, fileName, date, HttpMethod.GET)
            .toString();
        log.info("Url successfully generated");
        return preSignedUrl;
    }

    @Override
    public Optional<String> getFileUrl(String fileName, String storageName) {
        log.info("Getting {}", fileName);
        if (amazonS3.doesObjectExist(storageName, fileName)) {
            return Optional.of(generateUrl(fileName, storageName));
        } else {
            log.warn("File doesn't exist: {}", fileName);
            return Optional.empty();
        }
    }

    @Override
    public String uploadTutorVideoPresentation(InputStream inputStream, String fileName) throws IOException {
        return uploadFile(inputStream, fileName, tutorsVideoPresentationStorageName);
    }

    @Override
    public String uploadStudentVideoPresentation(InputStream inputStream, String fileName) throws IOException {
        return uploadFile(inputStream, fileName, studentsVideoPresentationStorageName);
    }

    @Override
    public String uploadAvatar(InputStream inputStream, String fileName) throws IOException{
        return uploadFile(inputStream, fileName, avatarStorageName);
    }

    @Override
    public String getAvatarUrl(String fileName) {
        Optional<String> avatarUrl = getFileUrl(fileName, avatarStorageName);
        return avatarUrl.orElseGet(this::getDefaultAvatarUrl);
    }

    @Override
    public String getTutorVideoPresentationUrl(String fileName) {
        return getFileUrl(fileName, tutorsVideoPresentationStorageName)
            .orElse("");
    }

    @Override
    public String getStudentVideoPresentationUrl(String fileName) {
        return getFileUrl(fileName, studentsVideoPresentationStorageName)
            .orElse("");
    }

    @Override
    public Boolean deleteAvatar(String fileName) {
        return deleteFile(fileName, avatarStorageName);
    }

    @Override
    public Boolean deleteTutorVideoPresentation(String fileName) {
        return deleteFile(fileName, tutorsVideoPresentationStorageName);
    }

    @Override
    public Boolean deleteStudentVideoPresentation(String fileName) {
        return deleteFile(fileName, studentsVideoPresentationStorageName);
    }

    private String getDefaultAvatarUrl() {
        if (amazonS3.doesObjectExist(avatarStorageName, defaultAvatarName)) {
            return generateUrl(defaultAvatarName, avatarStorageName);
        } else {
            log.warn("Default avatar doesn't exist");
            return "";
        }
    }

}
