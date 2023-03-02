package com.example.convservice.client;

import com.example.convservice.dto.TutorCardInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service-tutors-card-info",
    url = "${services.user.url}/api/v1/users")
public interface UserClient {
    @GetMapping("/tutors/existing-conversations-details")
    List<TutorCardInfo> findTutorsWithExistingConvDetails();
}
