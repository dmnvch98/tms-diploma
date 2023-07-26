package com.example.apigateway.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.example.apigateway.controllers.utils.Utils.generateFile;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Component
public class ControllerMethodsUtils {
    @Autowired
    private MockMvc mockMvc;
    private final static String host = "/api/v1/files/";
    private final static String studentPresentationEndpoint = "video-presentation/student";
    private final static String tutorPresentationEndpoint = "video-presentation/tutor";
    private final static String avatarFilePath = "file-service/src/test/files/avatar.png";
    private final static String videoFilePath = "file-service/src/test/files/video.mp4";
    private final static String fileName = "fileName";
    private final static String localStackHost = "http://localstack:4566//";
    @Value("${video_presentation.tutor_postfix}")
    public String tutorPresentationPostfix;
    @Value("${video_presentation.student_postfix}")
    public String studentPresentationPostfix;
    @Value("${avatar.user_postfix}")
    public String avatarPostfix;
    @Value("${aws.students_video_presentation_storage_name}")
    public String studentsVideoPresentationStorageName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    public String tutorsVideoPresentationStorageName;
    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;

    public String uploadAvatar(String accessToken, Long userId) throws Exception {
        String expectedFileUrl = localStackHost + avatarStorageName + "/" + userId + avatarPostfix;

        return mockMvc.perform(multipart(host)
                .file(generateFile(avatarFilePath, "avatar"))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.fileName").value(userId + avatarPostfix))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)))
            .andReturn()
            .getResponse()
            .getContentAsString();
    }

    public String uploadTutorVideoPresentation(String accessToken, Long tutorId) throws Exception {
        MockMultipartFile file = generateFile(videoFilePath, fileName);
        String presentationFileName = tutorId + tutorPresentationPostfix;
        String expectedFileUrl = localStackHost + tutorsVideoPresentationStorageName + "/" + presentationFileName;

        return mockMvc.perform(multipart(host + tutorPresentationEndpoint)
                .file(file)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.fileName").value(presentationFileName))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)))
            .andReturn()
            .getResponse()
            .getContentAsString();
    }

    public String uploadStudentVideoPresentation(String accessToken, Long studentId) throws Exception {
        MockMultipartFile file = generateFile(videoFilePath, fileName);
        String presentationFileName = studentId + studentPresentationPostfix;
        String expectedFileUrl = localStackHost + studentsVideoPresentationStorageName + "/" + presentationFileName;

        return mockMvc.perform(multipart(host + studentPresentationEndpoint)
                .file(file)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.fileName").value(presentationFileName))
            .andExpect(jsonPath("$.fileUrl").value(containsString(expectedFileUrl)))
            .andReturn()
            .getResponse()
            .getContentAsString();
    }
}
