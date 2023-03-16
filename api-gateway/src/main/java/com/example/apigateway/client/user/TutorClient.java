package com.example.apigateway.client.user;

import com.example.apigateway.dto.FilterTutorsRequestDto;
import com.example.apigateway.dto.TutorShortUserInfoDto;
import com.example.apigateway.model.Tutor;
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

    @GetMapping("/not-booked-conversations-details")
    List<TutorShortUserInfoDto> findTutorsWhoHaveNotBookedConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId);

    @GetMapping("/not-booked-conversations-details/filter")
    List<TutorShortUserInfoDto> filterTutorsWhoHaveNotBookedConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId, @RequestBody FilterTutorsRequestDto dto);
}
