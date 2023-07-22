package com.example.fileloader.controllers;

import com.example.fileloader.config.TestBucketInitializer;
import com.example.fileloader.facade.FileFacade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;

import static com.example.fileloader.utils.FileUtils.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@AutoConfigureMockMvc
@Import(TestBucketInitializer.class)
@Slf4j
public class FileControllerIntegrationTest {
    @Value("${aws.students_video_presentation_storage_name}")
    public String studentsVideoPresentationStorageName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    public String tutorsVideoPresentationStorageName;
    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;
    @Value("${avatar.default}")
    public String defaultAvatarName;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

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
        Long userId = 123L;
        String fileName = getAvatarName(userId);
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/" +
            "file-service/src/test/files/avatar.png");
        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.IMAGE_PNG_VALUE, inputStream);
        String expectedFileUrl = "http://localhost:4566//avatars-test/" + userId + "_avatar.png";

        ResultActions resultActions = mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", userId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(getAvatarName(userId)))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testGetFilesList() throws Exception {
        Long userId1 = 123L;
        Long userId2 = 124L;
        String fileName1 = getAvatarName(userId1);
        String fileName2 = getAvatarName(userId2);

        uploadAvatar(userId1, fileName1);
        uploadAvatar(userId2, fileName2);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/files/{storageName}", avatarStorageName));

        resultActions.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0]").value(getAvatarName(userId1)))
            .andExpect(jsonPath("$[1]").value(getAvatarName(userId2)));
    }

    @Test
    void testGetAvatarUrl() throws Exception {
        Long userId = 123L;
        String fileName = getAvatarName(userId);
        uploadAvatar(userId, fileName);

        String expectedFileUrl = "http://localhost:4566//avatars-test/" + userId + "_avatar.png";

        mockMvc.perform(get("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));

    }

    @Test
    void testDeleteAvatar() throws Exception {
        Long userId = 123L;
        String fileName = getAvatarName(userId);
        uploadAvatar(userId, fileName);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    void testUploadDefaultAvatar() throws Exception {
        String fileName = fileFacade.defaultAvatarName;
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/" +
            "file-service/src/test/files/avatar.png");
        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.IMAGE_PNG_VALUE, inputStream);

        String expectedFileUrl = "http://localhost:4566//" + avatarStorageName + "/" + defaultAvatarName;

        mockMvc.perform(multipart("/api/v1/files/avatar/default")
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    private void uploadAvatar(Long userId, String fileName) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/" +
            "file-service/src/test/files/avatar.png");
        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.IMAGE_PNG_VALUE, inputStream);

        mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", userId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));
    }

    @Test
    void testUploadStudentVideoPresentation() throws Exception {
        Long studentId = 123L;
        String fileName = getStudentVideoPresentationName(studentId);
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/" +
            "file-service/src/test/files/video.mp4");

        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);

        String expectedFileUrl = "http://localhost:4566//" + studentsVideoPresentationStorageName + "/" + fileName;

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/files/video-presentation/student/{studentId}", studentId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testUploadTutorVideoPresentation() throws Exception {
        Long tutorId = 123L;
        String fileName = getTutorVideoPresentationName(tutorId);
        InputStream inputStream = getClass().getResourceAsStream("/Users/Yauhen/Documents/Yauhen/tms-diploma/" +
            "file-service/src/test/files/video.mp4");

        MockMultipartFile file = new MockMultipartFile("file", fileName, MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);

        String expectedFileUrl = "http://localhost:4566//" + tutorsVideoPresentationStorageName + "/" + fileName;

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/files/video-presentation/tutor/{tutorId}", tutorId)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }
}
