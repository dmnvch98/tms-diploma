package com.example.fileloader.interfaces;

public interface FileDeletionService {
    Boolean deleteFile(String fileName, String storageName);
    Boolean deleteAvatar(Long userId);
    Boolean deleteTutorVideoPresentation(Long tutorId);
    Boolean deleteStudentVideoPresentation(Long studentId);
}
