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

@RequiredArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;
    @Value("${aws.storage_name}")
    public String storageName;
    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    @Override
    public String uploadFile(InputStream inputStream, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        log.info("Started uploading {}", fileName);
        try {
            amazonS3.putObject(
                storageName,
                fileName,
                inputStream,
                metadata);
            log.info("{} successfully uploaded", fileName);
            return getAvatarUrl(fileName);
        } catch (RuntimeException e) {
            log.error("An error occurred while uploading {}: ", fileName + e);
        }
        return "";
    }

    @Override
    public List<String> getFilesList() {
        log.info("Getting list of all files");
        List<String> filesList = new ArrayList<>();
        try {
            filesList = amazonS3.listObjects(storageName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .toList();
            log.info("All files are successfully loaded. The list size is {}", filesList.size());
        } catch (Exception e) {
            log.error("An error occurred while loading all files: " + e);
        }
        return filesList;
    }

    @Override
    public Boolean deleteFile(String fileName) {
        try {
            log.info("Deleting avatar with name: {}", fileName);
            amazonS3.deleteObject(storageName, fileName);
            log.info("Avatar with name: {} successfully deleted", fileName);
            return true;
        } catch (Exception e) {
            log.error("Error during removing of avatar " + e);
        }
        return false;
    }

    private String generateUrl(String fileName) {
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
    public String getAvatarUrl(String fileName) {
        log.info("Getting {}", fileName);
        if (amazonS3.doesObjectExist(storageName, fileName)) {
            return generateUrl(fileName);
        } else {
            log.warn("{} doesn't exist. Getting default avatar", fileName);
            if (amazonS3.doesObjectExist(storageName, defaultAvatarName)) {
                return generateUrl(defaultAvatarName);
            } else {
                log.warn("Default avatar doesn't exist");
                return "";
            }
        }
    }
}
