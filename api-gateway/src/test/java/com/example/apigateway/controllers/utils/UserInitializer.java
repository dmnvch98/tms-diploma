package com.example.apigateway.controllers.utils;

import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.apigateway.controllers.utils.Utils.createUserRequestDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@Getter
@Data
public class UserInitializer {
    @Autowired
    private MockMvc mockMvc;
    private UserResponseDto userResponseDto;
    private UserRequestDto userRequestDto;
    private Student student;
    private Tutor tutor;
    private String accessToken;
    @Autowired
    private ObjectMapper objectMapper;

    public void createUserAndToken() throws Exception {
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
}
