package com.example.apigateway.client;

import com.example.apigateway.dto.TutorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service-tutors",
        url = "${services.user.url}/api/v1/tutors")
public interface TutorClient {
    @PutMapping
    TutorDto updateTutor(@RequestBody TutorDto tutorDto);

    @PostMapping
    TutorDto saveTutor(@RequestBody TutorDto tutorDto);

    @DeleteMapping("/{userId}")
    String deleteTutor(@PathVariable("userId") Long userId);
}
