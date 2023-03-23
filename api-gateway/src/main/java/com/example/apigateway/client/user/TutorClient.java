package com.example.apigateway.client.user;

import com.example.apigateway.model.Tutor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service-tutors",
    url = "${services.user.url}/api/v1/tutors")
public interface TutorClient {
    @DeleteMapping("/{userId}")
    void deleteTutor(@PathVariable("userId") Long userId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Tutor save(@RequestBody Tutor tutor);
}
