package com.example.apigateway.client.conversation;

import com.example.apigateway.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @GetMapping("/details/tutor/{tutorId}")
    List<ConversationDetailsResponseDto> getTutorConversationDetails(@PathVariable("tutorId") Long tutorId);

    @PostMapping("/")
    ConversationResponseDto saveConversation(@RequestBody ConversationRequestDto dto);

    @GetMapping("/students/{studentId}")
    List<ConversationResponseDto> findAllByStudentId(@PathVariable("studentId") Long studentId);

    @GetMapping("/tutors/{tutorId}")
    List<ConversationResponseDto> findAllByTutorId(@PathVariable("tutorId") Long tutorId);

    @GetMapping("/{convId}/student/{studentId}/exists")
    Boolean countAllByConvIdAndStudentId(@PathVariable("convId") Long convId,
                                         @PathVariable("studentId") Long studentId);

    @GetMapping("/{convId}/tutor/{tutorId}/exists")
    Boolean countAllByConvIdAndTutorId(@PathVariable("convId") Long convId,
                                                      @PathVariable("tutorId") Long tutorId);
}
