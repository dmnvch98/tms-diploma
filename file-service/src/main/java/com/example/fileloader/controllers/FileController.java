package com.example.fileloader.controllers;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.facade.FileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileFacade fileFacade;

    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadAvatar(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        return fileFacade.uploadAvatar(file.getInputStream(), userId);
    }

    @GetMapping("/{storageName}")
    public List<String> getFilesList(@PathVariable String storageName) {
        return fileFacade.getFilesList(storageName);
    }

    @GetMapping("avatar/{userId}")
    public ResponseDto getAvatarUrl(@PathVariable("userId") final Long userId) {
        return fileFacade.getAvatarUrl(userId);
    }

    @DeleteMapping("avatar/{userId}")
    public ResponseEntity<Boolean> deleteAvatar(@PathVariable final Long userId) {
        return fileFacade.deleteAvatar(userId);
    }

    @PostMapping(value = "/default-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadDefaultAvatar(@RequestPart("file") final MultipartFile file) throws IOException {
        return fileFacade.uploadDefaultAvatar(file.getInputStream());
    }

    @PostMapping(value = "video-presentation/student/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadStudentVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      @PathVariable("studentId") Long studentId) throws IOException {
        return fileFacade.uploadStudentVideoPresentation(file.getInputStream(), studentId);
    }

    @PostMapping(value = "video-presentation/tutor/{tutorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadTutorVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      @PathVariable("tutorId") Long tutorId) throws IOException {
        return fileFacade.uploadTutorVideoPresentation(file.getInputStream(), tutorId);
    }

    @GetMapping("video-presentation/student/{studentId}")
    public ResponseDto getStudentVideoPresentationUrl(@PathVariable("studentId") final Long studentId) {
        return fileFacade.getStudentVideoPresentationUrl(studentId);
    }

    @GetMapping("video-presentation/tutor/{tutorId}")
    public ResponseDto getTutorVideoPresentationUrl(@PathVariable("tutorId") final Long tutorId) {
        return fileFacade.getTutorVideoPresentationUrl(tutorId);
    }

    @DeleteMapping("video-presentation/student/{studentId}")
    public ResponseEntity<Boolean> deleteStudentVideoPresentation(@PathVariable final Long studentId) {
        return fileFacade.deleteStudentVideoPresentation(studentId);
    }

    @DeleteMapping("video-presentation/tutor/{tutorId}")
    public ResponseEntity<Boolean> deleteTutorVideoPresentation(@PathVariable final Long tutorId) {
        return fileFacade.deleteTutorVideoPresentation(tutorId);
    }
}
