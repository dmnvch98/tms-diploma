package com.example.convservice.client;

import com.example.convservice.dto.FilterTutorsRequestDto;
import com.example.convservice.dto.TutorShortUserInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service-tutors-card-info",
    url = "${services.user.url}/api/v1/tutors")
public interface TutorClient {
    @GetMapping("/existing-conversations-details")
    List<TutorShortUserInfoDto> findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId);

    @GetMapping("/existing-conversations-details/filter")
    List<TutorShortUserInfoDto> filterTutors(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId, @RequestBody FilterTutorsRequestDto dto);
}
