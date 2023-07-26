package com.example.apigateway.facades;

import com.example.apigateway.dto.ResponseDto;
import com.example.apigateway.model.Student;
import com.example.apigateway.model.Tutor;
import com.example.apigateway.services.FileService;
import com.example.apigateway.services.StudentService;
import com.example.apigateway.services.TutorService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class FileFacade {
    private final FileService fileService;
    private final UserService userService;
    private final TutorService tutorService;
    private final StudentService studentService;

    public ResponseDto uploadFile(final MultipartFile file, final Long userId) {
        return fileService.uploadFile(file, userId);
    }

    public ResponseDto getAvatarUrl(Long userId) {
        return fileService.getAvatarUrl(userId);
    }

    public Boolean deleteAvatar(Long userId) {
        return fileService.deleteAvatar(userId);
    }

    public ResponseDto uploadStudentVideoPresentation(final MultipartFile file, final Long userId) throws IOException {
        Student student = userService.get(userId).getStudent();
        ResponseDto responseDto = fileService.uploadStudentVideoPresentation(file, student.getStudentId());
        student.setPresentationFileName(responseDto.getFileName());
        studentService.update(student);

        return responseDto;
    }

    public ResponseDto uploadTutorVideoPresentation(final MultipartFile file, Long userId) throws IOException {
        Tutor tutor = userService.get(userId).getTutor();
        ResponseDto responseDto = fileService.uploadTutorVideoPresentation(file, tutor.getTutorId());
        tutor.setPresentationFileName(responseDto.getFileName());
        tutorService.update(tutor);
        return responseDto;
    }


    public ResponseDto getStudentVideoPresentationUrl(final Long studentId) {
        return fileService.getStudentVideoPresentationUrl(studentId);
    }

    public ResponseDto getTutorVideoPresentationUrl(final Long tutorId) {
        return fileService.getTutorVideoPresentationUrl(tutorId);
    }

    public ResponseEntity<Boolean> deleteStudentVideoPresentation(final Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return fileService.deleteStudentVideoPresentation(studentId);
    }

    public ResponseEntity<Boolean> deleteTutorVideoPresentation(final Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return fileService.deleteTutorVideoPresentation(tutorId);
    }

    public ResponseEntity<String> getDefaultAvatar() {
        return fileService.getDefaultAvatar();
    }
}
