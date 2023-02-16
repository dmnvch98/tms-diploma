package com.example.apigateway.dto;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.Builder;
import lombok.Value;

import java.io.InputStream;

@Builder
@Value
public class FileDto {
    String name;
    InputStream inputStream;
    ObjectMetadata metadata;
}
