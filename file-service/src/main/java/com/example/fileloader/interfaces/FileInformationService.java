package com.example.fileloader.interfaces;

import java.util.List;
import java.util.Optional;

public interface FileInformationService {
    List<String> getFilesList(String storageName);
    Optional<String> getFileUrl(String fileName, String storageName);
    String getAvatarUrl(Long userId);
    String getTutorVideoPresentationUrl(Long tutorId);
    String getStudentVideoPresentationUrl(Long studentId);
    String getAvatarName(Long userId);
    String getStudentVideoPresentationName(Long studentId);
    String getTutorVideoPresentationName(Long tutorId);
    String getDefaultAvatarUrl();
    String generateUrl(String fileName, String storageName);
}
