package com.example.fileloader.dto;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.io.InputStream;

@Builder
@Value
@Jacksonized
public class FileDto {
    String name;
    InputStream inputStream;
    ObjectMetadata metadata;

}
