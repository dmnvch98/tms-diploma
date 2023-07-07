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

}
