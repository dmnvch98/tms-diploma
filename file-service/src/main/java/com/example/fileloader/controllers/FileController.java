package com.example.fileloader.controllers;

import com.example.fileloader.dto.ResponseDto;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;
    @Value("${avatar.default}")
    public String defaultAvatarName;
    @Value("${video_presentation.tutor_postfix}")
    public String tutorVideoPresentationNamePostfix;
    @Value("${video_presentation.student_postfix}")
    public String studentVideoPresentationNamePostfix;

    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadAvatar(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        return ResponseDto
            .builder()
            .fileName(fileService.uploadAvatar(file.getInputStream(), userId + userAvatarNamePostfix))
            .build();
    }

    @GetMapping("/{storageName}")
    public List<String> getFilesList(@PathVariable String storageName) {
        return fileService.getFilesList(storageName);
    }

    @GetMapping("avatar/{userId}")
    public ResponseDto getAvatarUrl(@PathVariable("userId") final Long userId) {
        return ResponseDto.builder()
            .fileName(fileService.getAvatarUrl(userId + userAvatarNamePostfix))
            .build();
    }

    @DeleteMapping("avatar/{fileName}")
    public ResponseEntity<Boolean> deleteAvatar(@PathVariable final String fileName) {
        return ResponseEntity.ok(fileService.deleteAvatar(fileName));
    }

    @PostMapping(value = "/default-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadDefaultAvatar(@RequestPart("file") final MultipartFile file) throws IOException {
        return ResponseDto.builder()
            .fileName(fileService.uploadAvatar(file.getInputStream(), defaultAvatarName))
            .build();
    }

    @PostMapping(value = "video-presentation/student/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadStudentVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      @PathVariable("studentId") Long studentId) throws IOException {
        return fileService.uploadStudentVideoPresentation(file.getInputStream(), studentId);
    }

    @PostMapping(value = "video-presentation/tutor/{tutorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadTutorVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      @PathVariable("tutorId") Long tutorId) throws IOException {
        return fileService.uploadTutorVideoPresentation(file.getInputStream(), tutorId);
    }

    @GetMapping("video-presentation/student/{studentId}")
    public ResponseDto getStudentVideoPresentationUrl(@PathVariable("studentId") final Long studentId) {
        return ResponseDto.builder()
            .fileName(fileService.getStudentVideoPresentationUrl(studentId + studentVideoPresentationNamePostfix))
            .build();
    }

    @GetMapping("video-presentation/tutor/{tutorId}")
    public ResponseDto getTutorVideoPresentationUrl(@PathVariable("tutorId") final Long tutorId) {
        return ResponseDto.builder()
            .fileName(fileService.getTutorVideoPresentationUrl(tutorId + tutorVideoPresentationNamePostfix))
            .build();
    }

    @DeleteMapping("video-presentation/student/{studentId}")
    public ResponseEntity<Boolean> deleteStudentVideoPresentation(@PathVariable final Long studentId) {
        return ResponseEntity.ok(fileService
            .deleteStudentVideoPresentation(studentId + studentVideoPresentationNamePostfix));
    }

    @DeleteMapping("video-presentation/tutor/{tutorId}")
    public ResponseEntity<Boolean> deleteTutorVideoPresentation(@PathVariable final Long tutorId) {
        return ResponseEntity.ok(fileService
            .deleteTutorVideoPresentation(tutorId + tutorVideoPresentationNamePostfix));
    }
}
