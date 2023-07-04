package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.facades.FileFacade;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping(value = "/")
    public ResponseDto uploadFile(@RequestPart("file") final MultipartFile file, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.uploadFile(file, userId);
    }

    @GetMapping("/avatar/{userId}")
    public ResponseDto getFile(@PathVariable Long userId) {
        return fileFacade.getFile(userId);
    }

    @DeleteMapping("/avatar")
    public ResponseEntity<Boolean> deleteFile(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return ResponseEntity.ok(fileFacade.deleteAvatar(userId));
    }

    @PostMapping(value = "video-presentation/student", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadStudentVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      Authentication authentication)
        throws IOException {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.uploadStudentVideoPresentation(file, userId);
    }

    @PostMapping(value = "video-presentation/tutor", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadTutorVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                    Authentication authentication)
        throws IOException {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.uploadTutorVideoPresentation(file, userId);
    }

    @DeleteMapping("video-presentation/student")
    public ResponseEntity<Boolean> deleteStudentVideoPresentation(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.deleteStudentVideoPresentation(userId);
    }

    @DeleteMapping("video-presentation/tutor")
    public ResponseEntity<Boolean> deleteTutorVideoPresentation(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return fileFacade.deleteTutorVideoPresentation(userId);
    }


}
