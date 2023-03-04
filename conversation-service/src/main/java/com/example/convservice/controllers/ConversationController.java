package com.example.convservice.controllers;

import com.example.convservice.dto.*;
import com.example.convservice.facades.ConversationDetailsFacade;
import com.example.convservice.facades.ConversationFacade;
import com.example.convservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationDetailsFacade conversationDetailsFacade;
    private final ConversationFacade conversationFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/details")
    public ConversationDetailsResponseDto save(@RequestBody ConversationDetailsRequestDto dto) {
        return conversationDetailsFacade.save(dto);
    }

    @GetMapping("/details/tutor/{tutorId}")
    public List<ConversationDetailsResponseDto> getTutorConversations(@PathVariable("tutorId") Long tutorId) {
        return conversationDetailsFacade.findAllByTutorId(tutorId);
    }

    @PostMapping("/")
    public ConversationResponseDto saveConversation(@RequestBody ConversationRequestDto dto) {
        return conversationFacade.save(dto);
    }

    @CrossOrigin
    @GetMapping("/details/tutors/")
    public List<TutorCardInfoMinPrice> findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId) {
        return conversationDetailsFacade.findTutorCardInfoWithMinPrice(lastTutorId);
    }

    @GetMapping("/details/tutors/filter")
    public List<User> filterTutors(@RequestBody FilterTutorsRequestDto dto) {
        return conversationDetailsFacade.filterTutors(dto);
    }
}
