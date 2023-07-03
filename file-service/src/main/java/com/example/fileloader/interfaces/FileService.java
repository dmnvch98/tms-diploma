package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    String uploadFile(InputStream inputStream, String fileName, String storageName) throws IOException;
    List<String> getFilesList();
    Boolean deleteFile(String fileName);
    String getAvatarUrl(String fileName);
    String uploadTutorVideoPresentation(InputStream inputStream, String fileName) throws IOException;
    String uploadStudentVideoPresentation(InputStream inputStream, String fileName) throws IOException;
    String uploadAvatar(InputStream inputStream, String fileName) throws IOException;
}
