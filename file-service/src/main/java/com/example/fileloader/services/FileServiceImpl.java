package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Optional<String> uploadFile(InputStream inputStream, Long userId) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        String fileName = userId + "_avatar.png";
        log.info("Started uploading avatar for userId {}", userId );
        try {
            amazonS3.putObject(
                bucket.getName(),
                fileName,
                inputStream,
                metadata);
            log.info("Avatar for userId {} successfully uploaded", userId);
            return Optional.of(getFileUrl(fileName));
        } catch (Exception e) {
            log.error("An error occurred while uploading avatar: " + e);
            return Optional.empty();
        }
    }

    @Override
    public List<String> getFilesList() {
        return amazonS3.listObjects(bucket.getName())
            .getObjectSummaries()
            .stream()
            .map(S3ObjectSummary::getKey)
            .toList();
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

    @Override
    public String getFileUrl(String fileName) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(accessExpirationInstant);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
            new GeneratePresignedUrlRequest(bucket.getName(), fileName)
                .withMethod(HttpMethod.GET)
                .withExpiration(date);
        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest)
            .toString();
    }
}
