package com.example.apigateway.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.core.io.InputStreamResource;


@Builder
@Value
public class FileDto {
    InputStreamResource inputStreamResource;
}
