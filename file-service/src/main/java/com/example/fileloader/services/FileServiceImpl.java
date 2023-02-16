package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;
    private final Bucket bucket;

    @Override
    public void uploadFile(InputStream inputStream, Long userId) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        amazonS3.putObject(
            bucket.getName(),
            userId + "_avatar.png",
            inputStream,
            metadata);
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
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(bucket.getName(), fileName);
    }

    @Override
    public String getFileUrl(String fileName) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(accessExpirationInstant);
        return amazonS3.generatePresignedUrl(bucket.getName(), fileName, date, HttpMethod.GET).toString();
    }
}
