package com.example.userservice.client.conversation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conversation-service",
    url = "${services.conversation.url}/api/v1/conversations")
public interface ConversationsClient {

    @GetMapping("/students/{studentId}/finished")
    Integer countStudentLessons(@PathVariable("studentId") Long studentId);

    @GetMapping("/tutors/{tutorId}/finished")
    Integer countTutorLessons(@PathVariable("tutorId") Long tutorId);


}
