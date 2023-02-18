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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;
    private final Bucket bucket;
    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Override
    public Optional<String> uploadFile(InputStream inputStream, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        log.info("Started uploading {}", fileName);
        try {
            amazonS3.putObject(
                bucket.getName(),
                fileName,
                inputStream,
                metadata);
            log.info("{} successfully uploaded", fileName);
            return getAvatarUrl(fileName);
        } catch (Exception e) {
            log.error("An error occurred while uploading {}: ", fileName + e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<String>> getFilesList() {
        log.info("Getting list of all files");
        try {
            Optional<List<String>> filesList = Optional.of(amazonS3.listObjects(bucket.getName())
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .toList());
            log.info("All files are successfully loaded. The list size is {}", filesList.get().size());
            return filesList;
        } catch (Exception e) {
            log.error("An error occurred while loading all files: " + e);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteFile(String fileName) {
        try {
            log.info("Deleting avatar with name: {}", fileName);
            amazonS3.deleteObject(bucket.getName(), fileName);
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
            .generatePresignedUrl(bucket.getName(), fileName, date, HttpMethod.GET)
            .toString();
        log.info("Url successfully generated");
        return preSignedUrl;
    }

    @Override
    public Optional<String> getAvatarUrl(String fileName) {
        log.info("Getting {}", fileName);
        if (amazonS3.doesObjectExist(bucket.getName(), fileName)) {
            return Optional.of(generateUrl(fileName));
        } else {
            log.warn("{} doesn't exist. Getting default avatar", fileName);
            if (amazonS3.doesObjectExist(bucket.getName(), defaultAvatarName)) {
                return Optional.of(generateUrl(defaultAvatarName));
            } else {
                log.warn("Default avatar doesn't exist");
                return Optional.empty();
            }
        }
    }
}
