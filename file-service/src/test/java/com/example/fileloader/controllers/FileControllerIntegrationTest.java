package com.example.fileloader.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fileloader.config.TestBucketInitializer;
import com.example.fileloader.facade.FileFacade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@AutoConfigureMockMvc
@Import(TestBucketInitializer.class)
public class FileControllerIntegrationTest {
    @Autowired
    FileFacade fileFacade;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestBucketInitializer testBucketInitializer;

    @BeforeEach
    void setUp() {
        testBucketInitializer.initializeTestBuckets();
    }

    @AfterEach
    void tear() {
        testBucketInitializer.cleanupTestBuckets();
    }

    @Test
    void testUploadAvatar() throws Exception {
        // Arrange
        Long userId = 123L;
        String fileName = "test.png";
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/file-service/src/test/files/avatar.png");
        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.IMAGE_PNG_VALUE, inputStream);

        // Act
        ResultActions resultActions = mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", userId)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA));

        // Assert
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
