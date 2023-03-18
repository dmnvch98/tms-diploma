package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.FeedbackAboutStudentRequestDto;
import com.example.apigateway.dto.FeedbackAboutStudentResponseDto;
import com.example.apigateway.dto.FeedbackAboutTutorRequestDto;
import com.example.apigateway.dto.FeedbackAboutTutorResponseDto;
import com.example.apigateway.facades.FeedbackFacade;
import com.example.apigateway.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController {
    private final FeedbackFacade feedbackFacade;

    @PostMapping("/student")
    public Feedback saveFeedbackAboutStudent(@RequestBody FeedbackAboutStudentRequestDto dto, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return feedbackFacade.saveFeedbackAboutStudent(dto, userId);
    }

    @PostMapping("/tutor")
    public Feedback saveFeedbackAboutTutor(@RequestBody FeedbackAboutTutorRequestDto dto, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return feedbackFacade.saveFeedbackAboutTutor(dto, userId);
    }

    @GetMapping("/tutor/{tutorId}")
    public List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(@PathVariable("tutorId") Long tutorId) {
        return feedbackFacade.findFeedbacksAboutTutor(tutorId);
    }

    @GetMapping("/student/{studentId}")
    public List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(@PathVariable("studentId") Long studentId) {
        return feedbackFacade.findFeedbacksAboutStudent(studentId);
    }
}
