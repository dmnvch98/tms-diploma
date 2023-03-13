package com.example.apigateway.facades;

import com.example.apigateway.services.FileService;
import com.example.fileloader.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class FileFacade {
    private final FileService fileService;
    @Value("${avatar.default}")
    public String defaultAvatarName;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public ResponseDto uploadFile(final MultipartFile file, final Long userId) {
        return fileService.uploadFile(file, userId);
    }

    public ResponseDto getFile(Long userId) {
        return fileService.getFile(userId + userAvatarNamePostfix);
    }

    public Boolean deleteFile(Long userId) {
        return fileService.deleteFile(userId + userAvatarNamePostfix, userId);
    }

    public ResponseDto getDefaultAvatar() {
        return fileService.getFile(defaultAvatarName);
    }
}
