package com.example.userservice.controllers;

import com.example.userservice.dto.ConversationDetailsRequestDto;
import com.example.userservice.dto.ConversationDetailsResponseDto;
import com.example.userservice.facades.ConversationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationFacade conversationFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ConversationDetailsResponseDto save(@RequestBody ConversationDetailsRequestDto dto) {
        return conversationFacade.save(dto);
    }

}
