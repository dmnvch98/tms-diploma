package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileService {
    String uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException;
    List<String> getFilesList(String storageName);
    Boolean deleteFile(String fileName, String storageName);
    Optional<String> getFileUrl(String fileName, String storageName);
    String uploadTutorVideoPresentation(InputStream inputStream, Long tutorId) throws IOException;
    String uploadStudentVideoPresentation(InputStream inputStream, Long studentId) throws IOException;
    String uploadAvatar(InputStream inputStream, Long userId) throws IOException;
    String getAvatarUrl(Long userId);
    String getTutorVideoPresentationUrl(Long tutorId);
    String getStudentVideoPresentationUrl(Long studentId);
    Boolean deleteAvatar(Long userId);
    Boolean deleteTutorVideoPresentation(Long tutorId);
    Boolean deleteStudentVideoPresentation(Long studentId);
    String uploadDefaultAvatar(InputStream file) throws IOException;
    String getAvatarName(Long userId);
    String getStudentVideoPresentationName(Long studentId);
    String getTutorVideoPresentationName(Long tutorId);
    String getDefaultAvatarUrl();

}
