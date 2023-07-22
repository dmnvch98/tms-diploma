package com.example.fileloader.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class FileUtils {
    private static String studentVideoPresentationNamePostfix;
    private static String tutorVideoPresentationNamePostfix;
    private static String userAvatarNamePostfix;
    private static Environment environment;

    @Autowired
    public FileUtils(Environment environment) {
        FileUtils.environment = environment;
    }

    @PostConstruct
    public static void init() {
        studentVideoPresentationNamePostfix = environment.getProperty("video_presentation.student_postfix");
        tutorVideoPresentationNamePostfix = environment.getProperty("video_presentation.tutor_postfix");
        userAvatarNamePostfix = environment.getProperty("avatar.user_postfix");

        log.info("Initialized FileUtils with values: studentPostfix={}, tutorPostfix={}, userPostfix={}",
            studentVideoPresentationNamePostfix, tutorVideoPresentationNamePostfix, userAvatarNamePostfix);
    }

    public static String getStudentVideoPresentationName(Long studentId) {
        return studentId + studentVideoPresentationNamePostfix;
    }

    public static String getTutorVideoPresentationName(Long tutorId) {
        return tutorId + tutorVideoPresentationNamePostfix;
    }

    public static String getAvatarName(Long userId) {
        return userId + userAvatarNamePostfix;
    }
}
