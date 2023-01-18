package com.example.apigateway.client;

import com.example.apigateway.model.Level;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-service-levels",
        url = "${services.user.url}/api/v1/levels")
public interface LevelClient {

    @GetMapping
    List<Level> getAllLevels();
}
