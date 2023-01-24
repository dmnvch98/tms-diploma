package com.example.apigateway.client;

import com.example.apigateway.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service-students",
        url = "${services.user.url}/api/v1/students")
public interface StudentClient {

    @DeleteMapping("/{userId}")
    String deleteStudent(@PathVariable("userId") Long userId);


}
