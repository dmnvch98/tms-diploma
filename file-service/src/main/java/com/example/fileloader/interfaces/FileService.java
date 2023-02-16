package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    void uploadFile(InputStream inputStream, Long userId) throws IOException;
    List<String> getFilesList();
    void deleteFile(String fileName);
    String getFileUrl(String fileName);
}
