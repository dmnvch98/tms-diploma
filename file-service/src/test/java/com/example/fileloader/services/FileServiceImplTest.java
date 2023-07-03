package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.example.fileloader.interfaces.FileService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileServiceImplTest {
    private FileService fileService;
    @Value("${aws.storage_name}")
    public String storageName;
    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;
    @Value("${avatar.default}")
    public String defaultAvatarName;
    private final String fileName = "test.txt";
    @Mock
    private AmazonS3 amazonS3;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fileService = new FileServiceImpl(amazonS3);
    }

    @Test
    @DisplayName("Should return URL for existing file")
    public void getAvatarUrlExistingFile() throws MalformedURLException {

        when(amazonS3.doesObjectExist(eq(storageName), eq(fileName))).thenReturn(true);

        URL expectedUrl = new URL("http://test-bucket.s3.amazonaws.com/" + fileName);

        when(amazonS3.generatePresignedUrl(eq(storageName), eq(fileName), any(Date.class), eq(HttpMethod.GET)))
            .thenReturn(expectedUrl);

        String actualUrl = fileService.getFileUrl(fileName, avatarStorageName);

        Assertions.assertEquals(expectedUrl.toString(), actualUrl);
    }

    @Test
    @DisplayName("Should return URL for default avatar when file doesn't exist")
    public void getAvatarUrlDefaultAvatar() throws MalformedURLException {
        when(amazonS3.doesObjectExist(eq(storageName), eq(fileName))).thenReturn(false);
        when(amazonS3.doesObjectExist(eq(storageName), eq(defaultAvatarName))).thenReturn(true);

        URL expectedUrl = new URL("http://test-bucket.s3.amazonaws.com/" + defaultAvatarName);

        when(amazonS3.generatePresignedUrl(eq(storageName), eq(defaultAvatarName), any(Date.class), eq(HttpMethod.GET)))
            .thenReturn(expectedUrl);
        String actualUrl = fileService.getFileUrl(fileName, avatarStorageName);
        Assertions.assertEquals(expectedUrl.toString(), actualUrl);
    }

    @Test
    @DisplayName("Should return empty string when file and default avatar don't exist")
    public void getAvatarUrlNoAvatar() {

        when(amazonS3.doesObjectExist(eq(storageName), eq(fileName))).thenReturn(false);
        when(amazonS3.doesObjectExist(eq(storageName), eq(defaultAvatarName))).thenReturn(false);

        String actualUrl = fileService.getFileUrl(fileName, avatarStorageName);

        Assertions.assertEquals("", actualUrl);
    }
}









