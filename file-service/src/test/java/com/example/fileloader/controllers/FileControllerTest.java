package com.example.fileloader.controllers;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.exceptions.FileUploadException;
import com.example.fileloader.facade.FileFacade;
import com.example.fileloader.interfaces.FileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest({FileController.class})
class FileControllerTest {

    @MockBean
    FileFacade fileFacade;

    @MockBean
    FileService fileService;

    @Autowired
    private MockMvc mockMvc;

    private final MockMultipartFile file = new MockMultipartFile(
        "file",
        "test.png",
        MediaType.IMAGE_PNG_VALUE,
        "test data".getBytes()
    );

    private String fileUrl = "https://test-file-storage.com";
    final String fileName = "fileName";


    @Test
    @DisplayName("Should upload avatar and return ResponseDto")
    void uploadAvatar() throws Exception {
        ResponseDto expectedResponse = ResponseDto
            .builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.uploadAvatar(any(InputStream.class), any(Long.class))).willReturn(expectedResponse);

        mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", 1L)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        BDDMockito.then(fileFacade).should().uploadAvatar(any(InputStream.class), any(Long.class));
    }

    @Test
    @DisplayName("Should handle FileUploadException and return an error response")
    void handleFileUploadException() throws Exception {
        given(fileFacade.uploadAvatar(any(), any())).willThrow(new FileUploadException("An error occurred while uploading the file."));

        mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", 1L)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").exists())
            .andDo(print());

        BDDMockito.then(fileFacade).should().uploadAvatar(any(InputStream.class), any(Long.class));

    }
}