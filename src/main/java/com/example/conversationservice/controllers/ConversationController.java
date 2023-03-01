package com.example.conversationservice.controllers;

import com.example.conversationservice.dto.ConversationDetailsRequestDto;
import com.example.conversationservice.dto.ConversationDetailsResponseDto;
import com.example.conversationservice.dto.ConversationRequestDto;
import com.example.conversationservice.dto.ConversationResponseDto;
import com.example.conversationservice.facades.ConversationDetailsFacade;
import com.example.conversationservice.facades.ConversationFacade;
import com.example.conversationservice.model.User;
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

    @GetMapping("/details/tutors")
    public List<User> findTutorsWithExistingConvDetails() {
        return conversationDetailsFacade.findTutorsWithExistingConvDetails();
    }

    @GetMapping("/details/tutors/price/{price}/convTypeId/{convTypeId}/location/{location}/languageId/{languageId}/levelId/{levelId}")
    public List<User> filterTutors(@PathVariable double price, @PathVariable Long convTypeId,
                                   @PathVariable String location, @PathVariable Long languageId, @PathVariable Long levelId) {
        return conversationDetailsFacade.filterTutors(price, convTypeId, location, languageId, levelId);
    }
}
