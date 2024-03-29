package com.example.fileloader.controllers;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.exceptions.FileNotFoundException;
import com.example.fileloader.exceptions.FileUploadException;
import com.example.fileloader.exceptions.GetFileException;
import com.example.fileloader.exceptions.StorageNotFoundException;
import com.example.fileloader.exceptions.messages.ExceptionMessages;
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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.example.fileloader.exceptions.messages.ExceptionMessages.FILE_NOT_FOUND;
import static com.example.fileloader.exceptions.messages.ExceptionMessages.formatMessage;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    private final String fileUrl = "https://test-file-storage.com";
    private final String fileName = "fileName";
    private final String storageName = "storage";
    private final Long userId = 1L;

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
        given(fileFacade.uploadAvatar(any(), any())).willThrow(new FileUploadException(ExceptionMessages.FILE_UPLOAD_ERROR));

        mockMvc.perform(multipart("/api/v1/files/avatar/{userId}", 1L)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").exists());

        BDDMockito.then(fileFacade).should().uploadAvatar(any(InputStream.class), any(Long.class));

    }

    @Test
    @DisplayName("Should return list of existing files")
    void getFilesList() throws Exception {
        List<String> expectedFiles = Arrays.asList("file1.txt", "file2.txt");
        given(fileFacade.getFilesList(storageName)).willReturn(expectedFiles);

        mockMvc.perform(get("/api/v1/files/{storageName}", storageName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(expectedFiles.size())))
            .andExpect(jsonPath("$[0]").value(expectedFiles.get(0)))
            .andExpect(jsonPath("$[1]").value(expectedFiles.get(1)));
        then(fileFacade).should().getFilesList(storageName);
    }

    @Test
    @DisplayName("Should handle StorageNotFoundException and return an error response")
    void getFilesListNotExistingStorage() throws Exception {
        given(fileFacade.getFilesList(storageName)).willThrow(new StorageNotFoundException(storageName));

        mockMvc.perform(get("/api/v1/files/{storageName}", storageName))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").exists());

        then(fileFacade).should().getFilesList(storageName);
    }

    @Test
    @DisplayName("Should handle GetFileException and return an error response")
    void handleGetFileException() throws Exception {
        given(fileFacade.getFilesList(storageName)).willThrow(new GetFileException(fileName));

        mockMvc.perform(get("/api/v1/files/{storageName}", storageName))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.message").exists());

        then(fileFacade).should().getFilesList(storageName);
    }

    @Test
    @DisplayName("Should handle FileNotFoundException and return an error response")
    void handeFileNotFound() throws Exception {
        String errorMessage = formatMessage(FILE_NOT_FOUND, fileName);

        given(fileFacade.getAvatarUrl(userId)).willThrow(new FileNotFoundException(fileName));

        mockMvc.perform(get("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message").exists())
            .andExpect(jsonPath("$.message").value(errorMessage));
        then(fileFacade).should().getAvatarUrl(userId);
    }

    @Test
    @DisplayName("Should return ResponseDto")
    void getAvatar() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder().fileUrl(fileUrl).fileName(fileName).build();

        given(fileFacade.getAvatarUrl(userId)).willReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().getAvatarUrl(userId);
    }

    @Test
    @DisplayName("Should delete avatar and return true")
    void deleteAvatar() throws Exception {

        given(fileFacade.deleteAvatar(userId)).willReturn(true);

        mockMvc.perform(delete("/api/v1/files/avatar/{userId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"))
            .andDo(print());
    }

    @Test
    @DisplayName("Should upload default avatar and return ResponseDto")
    void uploadDefaultAvatar() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.uploadDefaultAvatar(any(InputStream.class))).willReturn(expectedResponse);

        mockMvc.perform(multipart("/api/v1/files/avatar/default")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().uploadDefaultAvatar(any(InputStream.class));
    }

    @Test
    @DisplayName("Should upload student video presentation and return ResponseDto")
    void uploadStudentVideoPresentation() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.uploadStudentVideoPresentation(any(InputStream.class), any(Long.class))).willReturn(expectedResponse);

        mockMvc.perform(multipart("/api/v1/files/video-presentation/student/{studentId}", 1L)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().uploadStudentVideoPresentation(any(InputStream.class), any(Long.class));
    }

    @Test
    @DisplayName("Should upload tutor video presentation and return ResponseDto")
    void uploadTutorVideoPresentation() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.uploadTutorVideoPresentation(any(InputStream.class), any(Long.class))).willReturn(expectedResponse);

        mockMvc.perform(multipart("/api/v1/files/video-presentation/tutor/{tutorId}", 1L)
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().uploadTutorVideoPresentation(any(InputStream.class), any(Long.class));
    }

    @Test
    @DisplayName("Should return student video presentation URL")
    void getStudentVideoPresentationUrl() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.getStudentVideoPresentationUrl(userId)).willReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/files/video-presentation/student/{studentId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().getStudentVideoPresentationUrl(userId);
    }

    @Test
    @DisplayName("Should return tutor video presentation URL")
    void getTutorVideoPresentationUrl() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.getTutorVideoPresentationUrl(userId)).willReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/files/video-presentation/tutor/{tutorId}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().getTutorVideoPresentationUrl(userId);
    }

    @Test
    @DisplayName("Should delete student video presentation and return true")
    void deleteStudentVideoPresentation() throws Exception {
        ResponseEntity<Boolean> responseEntity = ResponseEntity.ok(true);
        given(fileFacade.deleteStudentVideoPresentation(userId)).willReturn(responseEntity);

        mockMvc.perform(delete("/api/v1/files/video-presentation/student/{studentId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"))
            .andDo(print());
    }

    @Test
    @DisplayName("Should delete tutor video presentation and return true")
    void deleteTutorVideoPresentation() throws Exception {
        ResponseEntity<Boolean> responseEntity = ResponseEntity.ok(true);
        given(fileFacade.deleteTutorVideoPresentation(userId)).willReturn(responseEntity);

        mockMvc.perform(delete("/api/v1/files/video-presentation/tutor/{tutorId}", userId))
            .andExpect(status().isOk())
            .andExpect(content().string("true"))
            .andDo(print());
    }

    @Test
    @DisplayName("Should return default avatar")
    void getDefaultAvatar() throws Exception {
        ResponseDto expectedResponse = ResponseDto.builder()
            .fileUrl(fileUrl)
            .fileName(fileName)
            .build();

        given(fileFacade.getDefaultAvatar()).willReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/files/avatar/default"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fileName").exists())
            .andExpect(jsonPath("$.fileUrl").exists())
            .andExpect(jsonPath("$.fileName").value(expectedResponse.getFileName()))
            .andExpect(jsonPath("$.fileUrl").value(expectedResponse.getFileUrl()));

        then(fileFacade).should().getDefaultAvatar();
    }
}