package com.example.convservice.client;

import com.example.convservice.dto.TutorCardInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "user-service-tutors-card-info",
    url = "${services.user.url}/api/v1/tutors")
public interface TutorClient {
    @GetMapping("/existing-conversations-details")
    List<TutorCardInfo> findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId);
}
