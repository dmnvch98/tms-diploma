package com.example.apigateway.controllers;

import com.example.apigateway.converters.LanguageLevelConverter;
import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.LanguageLevelDto;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@SpringBootTest()
@AutoConfigureMockMvc
@Disabled
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LanguageLevelConverter languageLevelConverter;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private Student student;
    private Tutor tutor;
    private String accessToken;

    @BeforeEach
    public void setupTestUser() throws Exception {
        userRequestDto = null;
        userResponseDto = null;
        createUserAndToken();
    }

    @Test
    @DisplayName("Test get me")
    void testGetMe() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/users/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken))
            .andExpect(status().isOk());
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
            .presentationUrl("presentation filename url update tutor")
            .build();

        userRequestDto.setStudent(updatedStudent);
        userRequestDto.setTutor(updatedTutor);

        // Remove one language level from user and add another language level
        LanguageLevelDto languageLevelDto = createLanguageLevelDto(1L, "Belarusian", 6L, "Proficiency");
        List<LanguageLevelDto> languageLevelDtos = userRequestDto.getLanguageLevels();
        languageLevelDtos.remove(1);
        languageLevelDtos.add(languageLevelDto);

        userRequestDto.setLanguageLevels(languageLevelDtos);

        String response = mockMvc.perform(put("http://localhost:8080/api/v1/users/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .content(objectMapper.writeValueAsString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value(userRequestDto.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(userRequestDto.getLastName()))
            .andExpect(jsonPath("$.student.aboutMe").value(userRequestDto.getStudent().getAboutMe()))
            .andExpect(jsonPath("$.student.presentationFileName").value(userRequestDto.getStudent().getPresentationFileName()))
            .andExpect(jsonPath("$.tutor.aboutMe").value(userRequestDto.getTutor().getAboutMe()))
            .andExpect(jsonPath("$.tutor.presentationUrl").value(userRequestDto.getTutor().getPresentationUrl()))
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

    private void createUserAndToken() throws Exception {
        userRequestDto = createUserRequestDto();
        String createUserUrl = "/api/v1/users";
        String response = mockMvc.perform(post(createUserUrl)
                .content(objectMapper.writeValueAsString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        userResponseDto = objectMapper.readValue(response, UserResponseDto.class);
        student = userResponseDto.getStudent();
        tutor = userResponseDto.getTutor();
        accessToken = getJwtToken(userRequestDto.getEmail(), userRequestDto.getPassword());
    }

    private UserRequestDto createUserRequestDto() {
        List<LanguageLevelDto> languageLevelDtos = new ArrayList<>();
        languageLevelDtos.add(createLanguageLevelDto(7L, "English", 2L, "Elementary"));
        languageLevelDtos.add(createLanguageLevelDto(9L, "Finnish", 5L, "Advanced"));

        return UserRequestDto.builder()
            .firstName("Eugen")
            .lastName("Dzemyanovich")
            .email(generateEmail())
            .password("12345")
            .nationality(1L)
            .gender("male")
            .roles(List.of("Tutor"))
            .location("Minsk")
            .languageLevels(languageLevelDtos)
            .student(generateStudent("init about me", "init url"))
            .tutor(generateTutor("init about me", "init url"))
            .build();
    }

    private LanguageLevelDto createLanguageLevelDto(long langId, String langDesc, long levelId, String levelDesc) {
        Language language = Language.builder()
            .languageId(langId)
            .description(langDesc)
            .build();
        Level level = Level.builder()
            .levelId(levelId)
            .description(levelDesc)
            .build();
        return LanguageLevelDto.builder()
            .language(language)
            .level(level)
            .build();
    }

    private String getJwtToken(String email, String password) throws Exception {
        String url = "http://localhost:8080/api/v1/auth/login";
        CredentialsDto credentialsDto = CredentialsDto.builder()
            .email(email)
            .password(password)
            .build();

        String responseContent = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(credentialsDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        JsonNode responseJson = objectMapper.readTree(responseContent);
        return responseJson.get("accessToken").asText();
    }

    private Student generateStudent(String aboutMe, String presentationFileName) {
        return Student.builder()
            .aboutMe(aboutMe)
            .presentationFileName(presentationFileName)
            .build();
    }

    private Tutor generateTutor(String aboutMe, String presentationFileName) {
        return Tutor.builder()
            .aboutMe(aboutMe)
            .presentationUrl(presentationFileName)
            .build();
    }

    private String generateEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = dateFormat.format(new Date());

        String uniquePart = "example.com";

        return "test_" + currentTime + "@" + uniquePart;
    }

}
