package com.example.apigateway.client.conversation;

import com.example.convservice.dto.ConversationDetailsRequestDto;
import com.example.convservice.dto.ConversationDetailsResponseDto;
import com.example.convservice.dto.FilterTutorsRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "conversation-service",
    url = "${services.conversation.url}/api/v1/conversations")
public interface ConversationClient {

    @GetMapping("/tutors/{tutorId}/minPrice")
    double findTutorMinimumPrice(@PathVariable("tutorId") Long tutorId);

    @GetMapping("/tutors/{tutorId}/minPrice/filter")
    double findTutorMinimumPrice(@RequestBody FilterTutorsRequestDto dto, @PathVariable("tutorId") Long tutorId);

    @GetMapping("/tutors/total")
    int countAllTutorsWithConvDetails();

    @GetMapping("/tutors/total/filter")
    int countFilteredTutorsWithConvDetails(FilterTutorsRequestDto dto);

    @PostMapping("/details")
    ConversationDetailsResponseDto saveConversationDetails(@RequestBody ConversationDetailsRequestDto dto);
}
