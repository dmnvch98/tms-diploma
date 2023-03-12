package com.example.apigateway.client.conversation;

import com.example.apigateway.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "conversation-service-addresses",
    url = "${services.conversation.url}/api/v1/addresses")
public interface AddressClient {

    @GetMapping("/tutors/{tutorId}")
    List<AddressDto> findAddressesDistinctByTutorId(@PathVariable("tutorId") Long tutorId);
}
