package com.example.apigateway.client.user;

import com.example.apigateway.model.Tutor;
import com.example.convservice.dto.FilterTutorsRequestDto;
import com.example.userservice.dto.TutorShortUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-tutors",
    url = "${services.user.url}/api/v1/tutors")
public interface TutorClient {
    @DeleteMapping("/{userId}")
    void deleteTutor(@PathVariable("userId") Long userId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Tutor save(@RequestBody Tutor tutor);

    @GetMapping("/existing-conversations-details")
    List<TutorShortUserInfoDto> findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId);

    @GetMapping("/existing-conversations-details/filter")
    List<TutorShortUserInfoDto> filterTutors(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId, @RequestBody FilterTutorsRequestDto dto);
}
