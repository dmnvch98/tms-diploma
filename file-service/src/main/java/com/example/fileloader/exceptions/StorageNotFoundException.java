package com.example.fileloader.exceptions;

public class StorageNotFoundException extends RuntimeException {

    public StorageNotFoundException(String message) {
        super(message);
    }
}
