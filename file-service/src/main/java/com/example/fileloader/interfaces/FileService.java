package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileService {
    String uploadFile(InputStream inputStream, String fileName) throws IOException;
    List<String> getFilesList();
    Boolean deleteFile(String fileName);
    String getAvatarUrl(String fileName);
}
