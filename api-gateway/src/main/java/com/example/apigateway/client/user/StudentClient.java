package com.example.apigateway.client.user;

import com.example.apigateway.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service-students",
    url = "${services.user.url}/api/v1/students")
public interface StudentClient {
    @DeleteMapping("/{userId}")
    void deleteStudent(@PathVariable("userId") Long userId);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Student save(Student student);
}
