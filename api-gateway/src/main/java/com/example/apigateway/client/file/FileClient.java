package com.example.apigateway.client.file;

import com.example.apigateway.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "file-service",
    url = "${services.file.url}/api/v1/files")
public interface FileClient {

    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto uploadAvatar(@RequestPart("file") final MultipartFile file, @PathVariable("userId") Long userId);

    @GetMapping("avatar/{fileName}")
    ResponseDto getAvatarUrl(@PathVariable("fileName") String fileName);

    @GetMapping("avatar/{userId}")
    ResponseDto getAvatarUrl(@PathVariable("userId") Long userId);

    @DeleteMapping("avatar/{fileName}")
    ResponseEntity<Boolean> deleteAvatar(@PathVariable("fileName") String fileName);

    @PostMapping(value = "video-presentation/student/{studentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto uploadStudentVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                      @PathVariable("studentId") Long studentId) throws IOException;

    @PostMapping(value = "video-presentation/tutor/{tutorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseDto uploadTutorVideoPresentation(@RequestPart("file") final MultipartFile file,
                                                    @PathVariable("tutorId") Long tutorId) throws IOException;

    @GetMapping("video-presentation/student/{studentId}")
    ResponseDto getStudentVideoPresentationUrl(@PathVariable("studentId") final Long studentId);

    @GetMapping("video-presentation/tutor/{tutorId}")
    ResponseDto getTutorVideoPresentationUrl(@PathVariable("tutorId") final Long tutorId);

    @DeleteMapping("video-presentation/student/{studentId}")
    ResponseEntity<Boolean> deleteStudentVideoPresentation(@PathVariable("studentId") final Long studentId);

    @DeleteMapping("video-presentation/tutor/{tutorId}")
    ResponseEntity<Boolean> deleteTutorVideoPresentation(@PathVariable("tutorId") final Long tutorId);

}
