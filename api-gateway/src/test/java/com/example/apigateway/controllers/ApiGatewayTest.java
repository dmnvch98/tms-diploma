package com.example.apigateway.controllers;

import com.example.apigateway.controllers.utils.ControllerMethodsUtils;
import com.example.apigateway.controllers.utils.UserInitializer;
import com.example.apigateway.converters.LanguageLevelConverter;
import com.example.apigateway.dto.UserRequestDto;
import com.example.apigateway.dto.UserResponseDto;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "spring.config.location=classpath:/application-test.yaml")
@AutoConfigureMockMvc
@Getter
public abstract class ApiGatewayTest {
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
}
