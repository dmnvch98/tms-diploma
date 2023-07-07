package com.example.fileloader.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
    }

    public FileNotFoundException(String message) {
        super(message);
    }
}
