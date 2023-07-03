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

    @PostMapping(value = "/avatar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadAvatar(@RequestPart("file") final MultipartFile file, @PathVariable Long userId)
        throws IOException {
        return ResponseDto
            .builder()
            .message(fileService.uploadAvatar(file.getInputStream(), userId + userAvatarNamePostfix))
            .build();
    }

    @GetMapping("/")
    public List<String> getFilesList() {
        return fileService.getFilesList();
    }

    @GetMapping("/{fileName}")
    public ResponseDto getAvatarUrl(@PathVariable("fileName") final String fileName) {
        return ResponseDto.builder()
            .message(fileService.getAvatarUrl(fileName))
            .build();
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Boolean> deleteAvatar(@PathVariable final String fileName) {
        return ResponseEntity.ok(fileService.deleteFile(fileName));
    }

    @PostMapping(value = "/default-avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadDefaultAvatar(@RequestPart("file") final MultipartFile file) throws IOException {
        return ResponseDto.builder()
            .message(fileService.uploadAvatar(file.getInputStream(), defaultAvatarName))
            .build();
    }
}
