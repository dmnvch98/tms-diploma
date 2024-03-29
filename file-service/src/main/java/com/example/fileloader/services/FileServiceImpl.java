package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import com.example.fileloader.exceptions.FileNotFoundException;
import com.example.fileloader.exceptions.GetFileException;
import com.example.fileloader.exceptions.StorageNotFoundException;
import com.example.fileloader.exceptions.UrlGenerationException;
import com.example.fileloader.exceptions.FileUploadException;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static com.example.fileloader.exceptions.messages.ExceptionMessages.FILE_UPLOAD_ERROR;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;

    @Override
    public Optional<String> uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(inputStream.available());
        log.info("Uploading file {} in store: {}", fileName, storageName);
        try (inputStream) {
            amazonS3.putObject(
                storageName,
                fileName,
                inputStream,
                metadata);
            log.info("{} successfully uploaded", fileName);
            return getFileUrl(fileName, storageName, true);
        } catch (Exception e) {
            log.error(FILE_UPLOAD_ERROR, fileName, e);
            throw new FileUploadException(fileName);
        }
    }

    private boolean doesStorageExist(String storageName) {
        boolean bucketExists = amazonS3.doesBucketExistV2(storageName);
        if (!bucketExists) {
            log.warn("The specified bucket does not exist: {}", storageName);
            throw new StorageNotFoundException(storageName);
        }
        return true;
    }

    @Override
    public List<String> getFilesList(String storageName) {
        log.info("Getting list of all files");
        List<String> filesList = new ArrayList<>();
        try {
            if (doesStorageExist(storageName)) {
                filesList = amazonS3.listObjects(storageName)
                    .getObjectSummaries()
                    .stream()
                    .map(S3ObjectSummary::getKey)
                    .collect(Collectors.toList());
                log.info("All files are successfully loaded. The list size is {}", filesList.size());
            }
        } catch (StorageNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while loading all files: ", e);
            throw new GetFileException(storageName);
        }
        return filesList;
    }

    @Override
    public Boolean deleteFile(String fileName, String storageName) {
        try {
            log.info("Deleting file: {}", fileName);
            if (doesFileExist(fileName, storageName)) {
                amazonS3.deleteObject(storageName, fileName);
                log.info("File {} successfully deleted", fileName);
                return true;
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error during removing the file: " + e);
        }
        return false;
    }

    private String generateUrl(String fileName, String storageName) {
        log.info("Generating pre-signed url for {}", fileName);
        try {
            LocalDateTime now = LocalDateTime.now();
            Instant accessExpirationInstant = now.plusMinutes(2).atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(accessExpirationInstant);
            String preSignedUrl = amazonS3
                .generatePresignedUrl(storageName, fileName, date, HttpMethod.GET)
                .toString();
            log.info("Url successfully generated for {}", fileName);
            return preSignedUrl;
        } catch (Exception e) {
            log.error("An error occurred while generating the pre-signed URL for {}: ", fileName, e);
            throw new UrlGenerationException(e);
        }
    }


    @Override
    public boolean doesFileExist(String fileName, String storageName) {
        log.info("Checking if file exists: {}", fileName);
        boolean fileExists = amazonS3.doesObjectExist(storageName, fileName);
        if (!fileExists) {
            log.warn("The specified file does not exist: {}", fileName);
            throw new FileNotFoundException(fileName);
        }
        return true;
    }
    @Override
    public Optional<String> getFileUrl(String fileName, String storageName, boolean throwExceptionOnNotFound) {
        log.info("Getting {}", fileName);
        try {
            if (doesFileExist(fileName, storageName)) {
                return Optional.of(generateUrl(fileName, storageName));
            }
        } catch (FileNotFoundException e) {
            if (throwExceptionOnNotFound) {
                throw new FileNotFoundException(fileName);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getDefaultAvatarUrl(String fileName, String storageName) {
        return getFileUrl(fileName, storageName, true).orElse("");
    }

}
