package com.example.fileloader.controllers;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.facade.FileFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @Operation(summary = "Upload user avatar")
    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadAvatar(@Parameter(description = "Avatar image file", required = true) @RequestPart("file")
                                        final MultipartFile file,
        @Parameter(description = "User ID", required = true) @PathVariable @Min(1) Long userId) throws IOException {
        return fileFacade.uploadAvatar(file.getInputStream(), userId);
    }


    @Operation(summary = "Get list of files from the storage")
    @GetMapping("/{storageName}")
    public List<String> getFilesList(@Parameter(description = "Storage name", required = true) @PathVariable
                                         @NotBlank String storageName) {
        return fileFacade.getFilesList(storageName);
    }

    @Operation(summary = "Get user avatar URL by user ID")
    @GetMapping("avatar/{userId}")
    public ResponseDto getAvatarUrl(@Parameter(description = "User ID", required = true) @PathVariable
                                        @Min(1) Long userId) {
        return fileFacade.getAvatarUrl(userId);
    }

    @Operation(summary = "Delete user avatar by user ID")
    @DeleteMapping("avatar/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteAvatar(@Parameter(description = "User ID", required = true) @PathVariable @Min(1) Long userId) {
        return fileFacade.deleteAvatar(userId);
    }

    @Operation(summary = "Upload default avatar image")
    @PostMapping(value = "avatar/default", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadDefaultAvatar(@Parameter(description = "Default avatar image file", required = true)
                                               @RequestPart("file") final MultipartFile file) throws IOException {
        return fileFacade.uploadDefaultAvatar(file.getInputStream());
    }

    @Operation(summary = "Upload student video presentation")
    @PostMapping(value = "video-presentation/student/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadStudentVideoPresentation(@Parameter(description = "Video presentation file", required = true)
                                                          @RequestPart("file") final MultipartFile file,
        @Parameter(description = "Student ID", required = true) @PathVariable @Min(1) Long studentId) throws IOException {
        return fileFacade.uploadStudentVideoPresentation(file.getInputStream(), studentId);
    }

    @Operation(summary = "Upload tutor video presentation")
    @PostMapping(value = "video-presentation/tutor/{tutorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadTutorVideoPresentation(@Parameter(description = "Video presentation file", required = true)
                                                        @RequestPart("file") final MultipartFile file,
    @Parameter(description = "Tutor ID", required = true) @PathVariable @Min(1) Long tutorId) throws IOException {
        return fileFacade.uploadTutorVideoPresentation(file.getInputStream(), tutorId);
    }

    @Operation(summary = "Get student video presentation URL by student ID")
    @GetMapping("video-presentation/student/{studentId}")
    public ResponseDto getStudentVideoPresentationUrl(@Parameter(description = "Student ID", required = true)
                                                          @PathVariable @Min(1) Long studentId) {
        return fileFacade.getStudentVideoPresentationUrl(studentId);
    }

    @Operation(summary = "Get tutor video presentation URL by tutor ID")
    @GetMapping("video-presentation/tutor/{tutorId}")
    public ResponseDto getTutorVideoPresentationUrl(@Parameter(description = "Tutor ID", required = true)
                                                        @PathVariable @Min(1) Long tutorId) {
        return fileFacade.getTutorVideoPresentationUrl(tutorId);
    }

    @Operation(summary = "Delete student video presentation by student ID")
    @DeleteMapping("video-presentation/student/{studentId}")
    public ResponseEntity<Boolean> deleteStudentVideoPresentation(@Parameter(description = "Student ID", required = true)
                                                                      @PathVariable @Min(1) Long studentId) {
        return fileFacade.deleteStudentVideoPresentation(studentId);
    }

    @Operation(summary = "Delete tutor video presentation by tutor ID")
    @DeleteMapping("video-presentation/tutor/{tutorId}")
    public ResponseEntity<Boolean> deleteTutorVideoPresentation(@Parameter(description = "Tutor ID", required = true)
                                                                    @PathVariable @Min(1) Long tutorId) {
        return fileFacade.deleteTutorVideoPresentation(tutorId);
    }

    @Operation(summary = "Get default avatar image URL")
    @GetMapping("avatar/default")
    public ResponseDto getDefaultAvatar() {
        return fileFacade.getDefaultAvatar();
    }
}
