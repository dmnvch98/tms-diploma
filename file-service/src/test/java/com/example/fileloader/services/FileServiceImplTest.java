package com.example.fileloader.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.fileloader.interfaces.FileService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private final Long userId = 1L;
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

        String actualUrl = fileService.getFileUrl(fileName, avatarStorageName).orElse("");

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
        String actualUrl = fileService.getAvatarUrl(1L);
        Assertions.assertEquals(expectedUrl.toString(), actualUrl);
    }

    @Test
    @DisplayName("Should return empty string when file and default avatar don't exist")
    public void getAvatarUrlNoAvatar() {

        when(amazonS3.doesObjectExist(eq(storageName), eq(fileName))).thenReturn(false);
        when(amazonS3.doesObjectExist(eq(storageName), eq(defaultAvatarName))).thenReturn(false);

        String actualUrl = fileService.getFileUrl(fileName, avatarStorageName).orElse("");

        Assertions.assertEquals("", actualUrl);
    }

    @Test
    @DisplayName("Should upload file and return its URL")
    public void uploadFileAndGetFileUrl() throws IOException {

        byte[] fileContent = "This is a test file.".getBytes();
        InputStream inputStream = new ByteArrayInputStream(fileContent);

        URL expectedUrl = new URL("http://test-bucket.s3.amazonaws.com/" + defaultAvatarName);

        when(amazonS3.putObject(eq(storageName), eq(fileName), any(InputStream.class), any()))
            .thenReturn(null);

        when(amazonS3.generatePresignedUrl(eq(storageName), eq(fileName), any(Date.class), eq(HttpMethod.GET)))
            .thenReturn(expectedUrl);

        when(amazonS3.doesObjectExist(eq(storageName), eq(fileName))).thenReturn(true);

        String actualUrl = fileService.uploadFile(inputStream, fileName, storageName);

        verify(amazonS3, times(1)).putObject(eq(storageName), eq(fileName), any(InputStream.class), any());
        verify(amazonS3, times(1)).generatePresignedUrl(eq(storageName), eq(fileName), any(Date.class), eq(HttpMethod.GET));

        Assertions.assertEquals(expectedUrl.toString(), actualUrl);
    }

    @Test
    @DisplayName("Should handle error during file upload")
    public void handleUploadError() throws IOException {

        byte[] fileContent = "This is a test file.".getBytes();
        InputStream inputStream = new ByteArrayInputStream(fileContent);

        doThrow(new RuntimeException("Upload error")).when(amazonS3)
            .putObject(eq(storageName), eq(fileName), any(InputStream.class), any());

        String actualUrl = fileService.uploadFile(inputStream, fileName, storageName);

        verify(amazonS3, times(1)).putObject(eq(storageName), eq(fileName), any(InputStream.class), any());
        verify(amazonS3, never()).generatePresignedUrl(anyString(), anyString(), any(Date.class), any(HttpMethod.class));

        Assertions.assertEquals("", actualUrl);
    }

    @Test
    @DisplayName("Should return list of files")
    public void getFilesList_Successful() {
        String prefix = "prefix";

        S3ObjectSummary s3ObjectSummary1 = new S3ObjectSummary();
        S3ObjectSummary s3ObjectSummary2 = new S3ObjectSummary();

        ObjectListing objectListing = new ObjectListing();
        objectListing.setBucketName(storageName);
        objectListing.setPrefix(prefix);

        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        objectSummaries.add(s3ObjectSummary1);
        objectSummaries.add(s3ObjectSummary2);

        when(amazonS3.listObjects(eq(storageName))).thenReturn(objectListing);

        List<String> expectedFilesList = objectSummaries.stream()
            .map(S3ObjectSummary::getKey)
            .collect(Collectors.toList());

        List<String> actualFilesList = fileService.getFilesList(storageName);

        verify(amazonS3, times(1)).listObjects(eq(storageName));

        Assertions.assertEquals(expectedFilesList, actualFilesList);
    }


    @Test
    @DisplayName("Should handle error during file list retrieval")
    public void handleFilesListError() {

        doThrow(new RuntimeException("Error retrieving file list")).when(amazonS3)
            .listObjects(eq(storageName));

        List<String> actualFilesList = fileService.getFilesList(storageName);

        verify(amazonS3, times(1)).listObjects(eq(storageName));

        Assertions.assertEquals(new ArrayList<>(), actualFilesList);
    }

    @Test
    @DisplayName("Should delete file and return true")
    public void deleteFile_Successful() {
        when(amazonS3.doesObjectExist(storageName, fileName)).thenReturn(true);

        boolean result = fileService.deleteFile(fileName, storageName);

        verify(amazonS3, times(1)).doesObjectExist(storageName, fileName);

        verify(amazonS3, times(1)).deleteObject(storageName, fileName);

        Assertions.assertTrue(result);
    }


    @Test
    @DisplayName("Should throw exception and not delete file")
    public void deleteFile_unsuccessful() {
        when(amazonS3.doesObjectExist(storageName, fileName)).thenReturn(true);

        doThrow(new RuntimeException("Delete error")).when(amazonS3)
            .deleteObject(eq(storageName), eq(fileName));

        Boolean result = fileService.deleteFile(fileName, storageName);

        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Should not delete existing file and return false")
    public void shouldNotTryToDeleteExistingFile() {
        when(amazonS3.doesObjectExist(storageName, fileName)).thenReturn(false);

        Boolean result = fileService.deleteFile(fileName, storageName);

        Assertions.assertFalse(result);
    }

}









