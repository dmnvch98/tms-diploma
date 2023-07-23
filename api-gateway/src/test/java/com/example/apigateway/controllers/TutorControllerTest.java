package com.example.apigateway.controllers;

import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.LanguageLevelDto;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.Language;
import com.example.apigateway.model.Level;
import com.example.apigateway.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@SpringBootTest()
@AutoConfigureMockMvc
@Disabled
class TutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser = User.builder().build();
    private String accessToken;

    @BeforeEach
    public void setupTestUser() throws Exception {
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
    @DisplayName("Test update user")
    void testUpdateUser() throws Exception {
        UserRequestDto userRequestDto = createUserRequestDto();

        mockMvc.perform(put("http://localhost:8080/api/v1/users/")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .content(objectMapper.writeValueAsString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value(userRequestDto.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(userRequestDto.getLastName()));
    }

    private void createUserAndToken() throws Exception {
        UserRequestDto userRequestDto = createUserRequestDto();

        String createUserUrl = "/api/v1/users";
        mockMvc.perform(post(createUserUrl)
                .content(objectMapper.writeValueAsString(userRequestDto))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        testUser.setEmail(userRequestDto.getEmail());
        testUser.setPassword(userRequestDto.getPassword());
        accessToken = getJwtToken(testUser.getEmail(), testUser.getPassword());
    }

    private UserRequestDto createUserRequestDto() {
        Language language = Language.builder()
            .languageId(7L)
            .description("English")
            .build();
        Level level = Level.builder()
            .levelId(2L)
            .description("Elementary")
            .build();
        return UserRequestDto.builder()
            .firstName("Eugen")
            .lastName("Dzemyanovich")
            .email(generateEmail())
            .password("12345")
            .nationality(1L)
            .gender("male")
            .roles(List.of("Tutor"))
            .location("Minsk")
            .languageLevels(List.of(LanguageLevelDto.builder().level(level).language(language).build()))
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

    private String generateEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = dateFormat.format(new Date());

        String uniquePart = "example.com";

        return "test_" + currentTime + "@" + uniquePart;
    }
}
