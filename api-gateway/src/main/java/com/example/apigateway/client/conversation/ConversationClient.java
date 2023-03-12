package com.example.apigateway.client.conversation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conversation-service",
    url = "${services.conversation.url}/api/v1/conversations")
public interface ConversationClient {

    @GetMapping("/tutors/{tutorId}/minPrice")
    double findTutorMinimumPrice(@PathVariable("tutorId") Long tutorId);

    @GetMapping("/tutors/total")
    int countAllTutorsWithConvDetails();
}
