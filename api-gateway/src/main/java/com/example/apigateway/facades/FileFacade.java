package com.example.apigateway.facades;

import com.example.apigateway.dto.FileDto;
import com.example.apigateway.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class FileFacade {
    private final FileService fileService;
    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public String uploadFile(final InputStream inputStream, final Long userId) {
        FileDto file = FileDto.builder().inputStreamResource(new InputStreamResource(inputStream)).build();
        return fileService.uploadFile(file, userId);
    }

    public String getFile(Long userId) {
        return fileService.getFile(userId + userAvatarNamePostfix);
    }

    public Boolean deleteFile(Long userId) {
        return fileService.deleteFile(userId + userAvatarNamePostfix, userId);
    }

    public String getDefaultAvatar() {
        return fileService.getFile(defaultAvatarName);
    }
}
