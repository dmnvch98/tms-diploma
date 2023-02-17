package com.example.fileloader.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileService {
    Optional<String> uploadFile(InputStream inputStream, Long userId) throws IOException;
    List<String> getFilesList();
    Boolean deleteFile(String fileName);
    String getFileUrl(String fileName);
}
