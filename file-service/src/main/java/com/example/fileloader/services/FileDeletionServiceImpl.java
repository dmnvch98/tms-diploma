package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fileloader.interfaces.FileDeletionService;
import com.example.fileloader.interfaces.FileInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileDeletionServiceImpl implements FileDeletionService {
    private final AmazonS3 amazonS3;
    private final FileInformationService fileInformationService;
    @Value("${aws.avatar_storage_name}")
    public String avatarStorageName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    public String tutorsVideoPresentationStorageName;
    @Value("${aws.students_video_presentation_storage_name}")
    public String studentsVideoPresentationStorageName;

    @Override
    public Boolean deleteFile(String fileName, String storageName) {
        try {
            log.info("Deleting file: {}", fileName);
            if (amazonS3.doesObjectExist(storageName, fileName)) {
                amazonS3.deleteObject(storageName, fileName);
                log.info("File {} successfully deleted", fileName);
                return true;
            }
            log.info("File {} doesn't exists", fileName);
            return false;
        } catch (Exception e) {
            log.error("Error during removing the file: " + e);
        }
        return false;
    }

    @Override
    public Boolean deleteAvatar(Long userId) {
        return deleteFile(fileInformationService.getAvatarName(userId), avatarStorageName);
    }


    @Override
    public Boolean deleteTutorVideoPresentation(Long tutorId) {
        return deleteFile(fileInformationService.getTutorVideoPresentationName(tutorId), tutorsVideoPresentationStorageName);
    }

    @Override
    public Boolean deleteStudentVideoPresentation(Long studentId) {
        return deleteFile(fileInformationService.getStudentVideoPresentationName(studentId), studentsVideoPresentationStorageName);
    }
}
