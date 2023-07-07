package com.example.fileloader.utils;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

@UtilityClass
public class FileUtils {
    @Value("${video_presentation.student_postfix}")
    public String studentVideoPresentationNamePostfix;
    @Value("${video_presentation.tutor_postfix}")
    public String tutorVideoPresentationNamePostfix;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public String getStudentVideoPresentationName(Long studentId) {
        return studentId + studentVideoPresentationNamePostfix;
    }

    public String getTutorVideoPresentationName(Long tutorId) {
        return tutorId + tutorVideoPresentationNamePostfix;
    }

    public String getAvatarName(Long userId) {
        return userId + userAvatarNamePostfix;
    }
}
