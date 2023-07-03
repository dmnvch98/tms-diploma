package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    String uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException;
    List<String> getFilesList(String storageName);
    Boolean deleteFile(String fileName, String storageName);
    String getFileUrl(String fileName, String storageName);
    String uploadTutorVideoPresentation(InputStream inputStream, String fileName) throws IOException;
    String uploadStudentVideoPresentation(InputStream inputStream, String fileName) throws IOException;
    String uploadAvatar(InputStream inputStream, String fileName) throws IOException;
    String getAvatarUrl(String fileName);
    String getTutorVideoPresentationUrl(String fileName);
    String getStudentVideoPresentationUrl(String fileName);
    Boolean deleteAvatar(String fileName);
    Boolean deleteTutorVideoPresentation(String fileName);
    Boolean deleteStudentVideoPresentation(String fileName);
}
