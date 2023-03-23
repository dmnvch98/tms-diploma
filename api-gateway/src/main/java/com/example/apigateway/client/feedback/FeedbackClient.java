package com.example.apigateway.client.feedback;

import com.example.apigateway.dto.FeedbackAboutStudentRequestDto;
import com.example.apigateway.dto.FeedbackAboutStudentResponseDto;
import com.example.apigateway.dto.FeedbackAboutTutorRequestDto;
import com.example.apigateway.dto.FeedbackAboutTutorResponseDto;
import com.example.apigateway.model.Feedback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "conversation-service-feedbacks",
    url = "${services.conversation.url}/api/v1/feedbacks")
public interface FeedbackClient {
    @PutMapping("/tutor")
    Feedback saveFeedbackAboutStudent(@RequestBody FeedbackAboutStudentRequestDto dto);

    @PutMapping("/student")
    Feedback saveFeedbackAboutTutor(@RequestBody FeedbackAboutTutorRequestDto dto);

    @GetMapping("/tutor/{tutorId}")
    List<FeedbackAboutTutorResponseDto> findFeedbacksAboutTutor(@PathVariable("tutorId") Long tutorId);

    @GetMapping("/student/{studentId}")
    List<FeedbackAboutStudentResponseDto> findFeedbacksAboutStudent(@PathVariable("studentId") Long studentId);
}
