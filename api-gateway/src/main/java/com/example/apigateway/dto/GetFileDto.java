package com.example.apigateway.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetFileDto {
    String bucketName;
    String fileName;
}
