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

import java.io.IOException;
import java.io.InputStream;

import static com.example.fileloader.utils.FileUtils.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private static final String videoFilePath = "/files/video.mp4";

    private static final String avatarFilePath = "/files/avatar.png";

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

        String expectedFileUrl = "http://localhost:4566//avatars-test/" + userId + "_avatar.png";

        uploadAvatar(userId, fileName).andExpect(status().isOk())
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
    void testGetDefaultAvatarUrl() throws Exception {
        uploadDefaultAvatar();

        String expectedFileUrl = "http://localhost:4566//avatars-test/" + defaultAvatarName;

        mockMvc.perform(get("/api/v1/files/avatar/default"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));

    }

    @Test
    void testDeleteAvatar() throws Exception {
        Long userId = 123L;
        String fileName = getAvatarName(userId);
        uploadAvatar(userId, fileName);

        mockMvc.perform(delete("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }

    @Test
    void testUploadDefaultAvatar() throws Exception {
        String expectedFileUrl = "http://localhost:4566//" + avatarStorageName + "/" + defaultAvatarName;

        uploadDefaultAvatar()
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testUploadStudentVideoPresentation() throws Exception {
        Long studentId = 123L;
        String fileName = getStudentVideoPresentationName(studentId);

        String expectedFileUrl = "http://localhost:4566//" + studentsVideoPresentationStorageName + "/" + fileName;

        uploadStudentVideoPresentation(studentId, fileName)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testUploadTutorVideoPresentation() throws Exception {
        Long tutorId = 123L;
        String fileName = getTutorVideoPresentationName(tutorId);

        String expectedFileUrl = "http://localhost:4566//" + tutorsVideoPresentationStorageName + "/" + fileName;

        uploadTutorVideoPresentation(tutorId, fileName)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testGetStudentVideoPresentationUrl() throws Exception {
        Long studentId = 123L;
        String fileName = getStudentVideoPresentationName(studentId);
        String expectedFileUrl = "http://localhost:4566//" + studentsVideoPresentationStorageName + "/" + fileName;

        uploadStudentVideoPresentation(studentId, fileName);

        mockMvc.perform(get("/api/v1/files/video-presentation/student/{studentId}", studentId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testGetTutorVideoPresentationUrl() throws Exception {
        Long tutorId = 123L;
        String fileName = getTutorVideoPresentationName(tutorId);
        String expectedFileUrl = "http://localhost:4566//" + tutorsVideoPresentationStorageName + "/" + fileName;

        uploadTutorVideoPresentation(tutorId, fileName);

        mockMvc.perform(get("/api/v1/files/video-presentation/tutor/{tutorId}", tutorId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)));
    }

    @Test
    void testDeleteTutorVideoPresentation() throws Exception {
        Long tutorId = 123L;
        String fileName = getTutorVideoPresentationName(tutorId);

        uploadTutorVideoPresentation(tutorId, fileName);

        mockMvc.perform(delete("/api/v1/files/video-presentation/tutor/{tutorId}", tutorId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));

        mockMvc.perform(get("/api/v1/files/video-presentation/tutor/{tutorId}", tutorId))
            .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteStudentVideoPresentation() throws Exception {
        Long studentId = 123L;
        String fileName = getTutorVideoPresentationName(studentId);

        uploadStudentVideoPresentation(studentId, fileName);

        mockMvc.perform(delete("/api/v1/files/video-presentation/student/{studentId}", studentId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));

        mockMvc.perform(get("/api/v1/files/video-presentation/student/{studentId}", studentId))
            .andExpect(status().isNotFound());
    }


    private ResultActions uploadAvatar(Long userId, String fileName) throws Exception {
        MockMultipartFile file = generateFile(avatarFilePath, fileName);

        return mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", userId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));
    }

    private ResultActions uploadDefaultAvatar() throws Exception {
        MockMultipartFile file = generateFile(avatarFilePath, defaultAvatarName);

        return mockMvc.perform(multipart("/api/v1/files/avatar/default")
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));
    }

    private ResultActions uploadStudentVideoPresentation(Long studentId, String fileName) throws Exception {
        MockMultipartFile file = generateFile(videoFilePath, fileName);

        return mockMvc.perform(multipart("/api/v1/files/video-presentation/student/{studentId}", studentId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));
    }

    private ResultActions uploadTutorVideoPresentation(Long tutorId, String fileName) throws Exception {
        MockMultipartFile file = generateFile(videoFilePath, fileName);

        return mockMvc.perform(multipart("/api/v1/files/video-presentation/tutor/{tutorId}", tutorId)
            .file(file)
            .contentType(MediaType.MULTIPART_FORM_DATA));
    }

    private MockMultipartFile generateFile(String path, String fileName) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(path);
        return new MockMultipartFile("file", fileName, MediaType.MULTIPART_FORM_DATA_VALUE, inputStream);
    }
}
