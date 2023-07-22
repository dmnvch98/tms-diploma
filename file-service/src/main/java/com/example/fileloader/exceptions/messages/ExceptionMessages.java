package com.example.fileloader.exceptions.messages;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessages {
    public static final String FILE_UPLOAD_ERROR = "An error occurred while uploading the file: {}";
    public static final String STORAGE_NOT_FOUND = "Storage not found: {}";
    public static final String FILE_GET_ERROR = "An error occurred while getting files. Storage: {}";
    public static final String FILE_NOT_FOUND = "File not found: {}";

    public static String formatMessage(String message, String fileName) {
        return message.replaceFirst("\\{\\}", fileName);
    }

}
