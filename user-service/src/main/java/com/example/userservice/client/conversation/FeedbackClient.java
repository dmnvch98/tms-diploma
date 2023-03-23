package com.example.userservice.client.conversation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conversation-service-feedbacks",
    url = "${services.conversation.url}/api/v1/feedbacks")
public interface FeedbackClient {

    @GetMapping("/tutor/{tutorId}/rate")
    Double findAvgRateForTutor(@PathVariable("tutorId") Long tutorId);

    @GetMapping("/student/{studentId}/rate")
    Double findAvgRateForStudent(@PathVariable("studentId") Long studentId);
}
