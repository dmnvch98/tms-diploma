package com.example.fileloader.controllers;

import com.example.fileloader.config.AmazonS3Config;
import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.interfaces.FileService;
import com.example.fileloader.services.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
@TestPropertySource(locations = "classpath:/application-test.yaml")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
class FileControllerTest {
    @Value("${aws.region}")
    public String AWS_REGION;
    @Value("${aws.s3_endpoint}")
    public String S3_ENDPOINT;
    @Value("${aws.access_key}")
    public String login;
    @Value("${aws.secret_key}")
    public String password;
    private final AmazonS3Config amazonS3Config = new AmazonS3Config();
    private final FileService fileService = new FileServiceImpl(amazonS3Config.amazonS3(login, password, S3_ENDPOINT, AWS_REGION));
    private final FileController fileController = new FileController(fileService);

    @Test
    void uploadDefaultAvatar() throws IOException {
        File file = new File("/Users/Yauhen/Downloads/default_avatar.png");
        String name = "default_avatar";
        String originalFileName = "default_avatar.png";
        String contentType = "image/png";
        byte[] content = Files.readAllBytes(file.toPath());
        MockMultipartFile mockMultipartFile = new MockMultipartFile(name, originalFileName, contentType, content);

        ResponseDto response = fileController.uploadDefaultAvatar(mockMultipartFile);

        // assert the response
        assertEquals("Success", response.getMessage());

    }
}