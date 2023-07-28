package com.example.apigateway.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class TutorControllerTest extends ApiGatewayTest{
    private final static String endpoint = "http://localhost:8080/api/v1/tutors/";

    @Test
    @DisplayName("Should delete existing tutor")
    void deleteTutor() throws Exception {
        saveTutor();

        getMockMvc().perform(delete(endpoint)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should handle case when deleting the Tutor entity when user has no Tutor role")
    void deleteTutorWithNoTutorRole() throws Exception {
        getMockMvc().perform(delete(endpoint)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()))
            .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Should save a new tutor")
    void save() throws Exception {
        saveTutor()
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.tutorId").exists())
            .andExpect(jsonPath("$.userId").value(getUserResponseDto().getId()))
            .andExpect(jsonPath("$.aboutMe").isEmpty())
            .andExpect(jsonPath("$.presentationFileName").isEmpty());

    }

    private ResultActions saveTutor() throws Exception {
        return getMockMvc().perform(post(endpoint)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken()));
    }


}