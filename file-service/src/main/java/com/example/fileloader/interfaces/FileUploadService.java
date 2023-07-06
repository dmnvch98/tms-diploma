package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;

public interface FileUploadService {
    String uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException;
    String uploadTutorVideoPresentation(InputStream inputStream, Long tutorId) throws IOException;
    String uploadStudentVideoPresentation(InputStream inputStream, Long studentId) throws IOException;
    String uploadAvatar(InputStream inputStream, Long userId) throws IOException;
    String uploadDefaultAvatar(InputStream file) throws IOException;
}

