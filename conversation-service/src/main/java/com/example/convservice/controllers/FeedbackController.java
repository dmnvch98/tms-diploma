package com.example.convservice.controllers;

import com.example.convservice.dto.FeedbackAboutStudentRequestDto;
import com.example.convservice.dto.FeedbackAboutStudentResponseDto;
import com.example.convservice.dto.FeedbackAboutTutorRequestDto;
import com.example.convservice.dto.FeedbackAboutTutorResponseDto;
import com.example.convservice.facades.FeedbackFacade;
import com.example.convservice.model.Feedback;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController {

    private final FeedbackFacade feedbackFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/tutor")
    public Feedback saveFeedbackAboutStudent(@RequestBody FeedbackAboutStudentRequestDto dto) {
        return feedbackFacade.saveFeedbackAboutStudent(dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/student")
    public Feedback saveFeedbackAboutTutor(@RequestBody FeedbackAboutTutorRequestDto dto) {
        return feedbackFacade.saveFeedbackAboutTutor(dto);
    }

    @GetMapping("/tutor/{tutorId}")
    public List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(@PathVariable("tutorId") Long tutorId) {
        return feedbackFacade.findFeedbacksAboutTutor(tutorId);
    }

    @GetMapping("/student/{studentId}")
    public List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(@PathVariable("studentId") Long studentId) {
        return feedbackFacade.findFeedbacksAboutStudent(studentId);
    }

    @GetMapping("/tutor/{tutorId}/rate")
    public Double findAvgRateForTutor(@PathVariable("tutorId") Long tutorId) {
        return feedbackFacade.findAvgRateForTutor(tutorId);
    }

    @GetMapping("/student/{studentId}/rate")
    public Double findAvgRateForStudent(@PathVariable("studentId") Long studentId) {
        return feedbackFacade.findAvgRateForStudent(studentId);
    }
}