package com.example.fileloader.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class StorageDto {
    String storageName;
}
