package com.example.apigateway.controllers;

import com.example.apigateway.controllers.utils.ControllerMethodsUtils;
import com.example.apigateway.controllers.utils.UserInitializer;
import com.example.apigateway.controllers.utils.Utils;
import com.example.apigateway.converters.LanguageLevelConverter;
import com.example.apigateway.dto.LanguageLevelDto;
import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.IntStream;

import static com.example.apigateway.controllers.utils.Utils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@AutoConfigureMockMvc
@Slf4j
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LanguageLevelConverter languageLevelConverter;
    @Autowired
    private UserInitializer userInitializer;
    @Autowired
    private ControllerMethodsUtils controllerMethodsUtils;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private Student student;
    private Tutor tutor;
    private String accessToken;
    private final String url = "http://localhost:8080/api/v1/users";

    @BeforeEach
    public void setupTestUserAndToken() throws Exception {
        userInitializer.createUserAndToken();
        student = userInitializer.getStudent();
        tutor = userInitializer.getTutor();
        userResponseDto = userInitializer.getUserResponseDto();
        userRequestDto = userInitializer.getUserRequestDto();
        accessToken = userInitializer.getAccessToken();
    }

    @Test
    @DisplayName("Test get me with avatar and video presentations")
    void testGetMe() throws Exception {
        uploadAvatarAndPresentations();
        String response = mockMvc.perform(get(url + "/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(userResponseDto, parseUserRespDtoFromJson(response));
    }

    @Test
    @DisplayName("Test update user first name, last name and language levels")
    void testUpdateUser() throws Exception {
        userRequestDto.setFirstName("Update first name");
        userRequestDto.setLastName("Update last name");

        Student updatedStudent = Student.builder()
            .studentId(student.getStudentId())
            .userId(userResponseDto.getId())
            .aboutMe("student about me update")
            .presentationFileName("presentation filename url update student")
            .build();

        Tutor updatedTutor = Tutor.builder()
            .tutorId(tutor.getTutorId())
            .userId(userResponseDto.getId())
            .aboutMe("tutor about me update")
            .presentationFileName("presentation filename url update tutor")
            .build();

        userRequestDto.setStudent(updatedStudent);
        userRequestDto.setTutor(updatedTutor);

        // Remove one language level from user and add another language level
        LanguageLevelDto languageLevelDto = createLanguageLevelDto(1L, "Belarusian", 6L, "Proficiency");
        List<LanguageLevelDto> languageLevelDtos = userRequestDto.getLanguageLevels();
        languageLevelDtos.remove(1);
        languageLevelDtos.add(languageLevelDto);

        userRequestDto.setLanguageLevels(languageLevelDtos);

        String response = mockMvc.perform(put(url)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .content(objectMapper.writeValueAsString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value(userRequestDto.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(userRequestDto.getLastName()))
            .andExpect(jsonPath("$.student.aboutMe").value(userRequestDto.getStudent().getAboutMe()))
            .andExpect(jsonPath("$.student.presentationFileName").value(userRequestDto.getStudent().getPresentationFileName()))
            .andExpect(jsonPath("$.tutor.aboutMe").value(userRequestDto.getTutor().getAboutMe()))
            .andExpect(jsonPath("$.tutor.presentationFileName").value(userRequestDto.getTutor().getPresentationFileName()))
            .andReturn()
            .getResponse()
            .getContentAsString();

        UserResponseDto userResponse = objectMapper.readValue(response, UserResponseDto.class);

        IntStream.range(0, userRequestDto.getLanguageLevels().size())
            .forEach(index -> {
                LanguageLevelDto expectedLanguageLevelDto = userRequestDto.getLanguageLevels().get(index);
                LanguageLevelDto actualLanguageLevelDto = languageLevelConverter
                    .languageLevelToDto(userResponse.getLanguageLevels().get(index));
                Assertions.assertEquals(expectedLanguageLevelDto, actualLanguageLevelDto);
            });
    }

    @Test
    @DisplayName("Find user by tutor id")
    public void findUserByTutorId() throws Exception {
        uploadAvatarAndPresentations();

        String response = mockMvc.perform(get(url + "/tutors/" + userResponseDto.getTutor().getTutorId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        Assertions.assertEquals(userResponseDto, parseUserRespDtoFromJson(response));
    }

    @DisplayName("Find user by student id")
    @Test
    public void findUserByStudentId() throws Exception {
        uploadAvatarAndPresentations();

        String response = mockMvc.perform(get(url + "/students/" + userResponseDto.getStudent().getStudentId())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
        Assertions.assertEquals(userResponseDto, parseUserRespDtoFromJson(response));
    }

    private void uploadAvatarAndPresentations() throws Exception {
        String uploadAvatarResponse = controllerMethodsUtils.uploadAvatar(accessToken, userResponseDto.getId());
        ResponseDto avatarResponseDto = Utils.parseJsonToObject(uploadAvatarResponse, ResponseDto.class);

        String uploadTutorVideoStudentPresResponse = controllerMethodsUtils
            .uploadTutorVideoPresentation(accessToken, userResponseDto.getTutor().getTutorId());
        ResponseDto responseDtoTutorPres = Utils.parseJsonToObject(uploadTutorVideoStudentPresResponse, ResponseDto.class);

        String uploadStudentVideoStudentPresResponse = controllerMethodsUtils
            .uploadStudentVideoPresentation(accessToken, userResponseDto.getStudent().getStudentId());
        ResponseDto responseDtoStudentPres = Utils.parseJsonToObject(uploadStudentVideoStudentPresResponse, ResponseDto.class);

        userResponseDto.setAvatarName(avatarResponseDto.getFileName());
        userResponseDto.getTutor().setPresentationFileName(responseDtoTutorPres.getFileName());
        userResponseDto.getStudent().setPresentationFileName(responseDtoStudentPres.getFileName());
    }

}
