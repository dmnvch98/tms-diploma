package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.dto.*;
import com.example.apigateway.facades.ConversationDetailsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationDetailsFacade conversationDetailsFacade;

    @GetMapping("/details/tutors/")
    public TutorCardsResponseDto findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId) {
        return conversationDetailsFacade.findTutorCardInfoWithMinPrice(lastTutorId);
    }

    @GetMapping("/details/tutors/filter")
    public TutorCardsResponseDto filterTutors(
        @RequestParam(value = "lastTutorId", defaultValue = "0", required = false) Long lastTutorId,
        @RequestParam(value = "minPrice", defaultValue = "0", required = false) Double minPrice,
        @RequestParam(value = "maxPrice", required = false) Double maxPrice,
        @RequestParam(value = "countryId", required = false) Long countryId,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "convTypeId", required = false) Long convTypeId,
        @RequestParam(value = "minLevelId", required = false) Long minLevelId,
        @RequestParam(value = "languageId", required = false) Long languageId
    ) {
        if (city != null && city.equals("")) {
            city = null;
        }
        FilterTutorsRequestDto dto = FilterTutorsRequestDto
            .builder()
            .minPrice(minPrice)
            .maxPrice(maxPrice)
            .countryId(countryId)
            .city(city)
            .conversationTypeId(convTypeId)
            .languageId(languageId)
            .levelId(minLevelId)
            .build();
        return conversationDetailsFacade.filterTutors(lastTutorId, dto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/details")
    public ConversationDetailsResponseDto saveConversationDetails(@RequestBody ConversationDetailsRequestDto dto, Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();
        return conversationDetailsFacade.saveConversationDetails(dto, userId);
    }

    @GetMapping("/details/tutor/{tutorId}")
    public List<ConversationDetailsResponseDto> getTutorConversationDetails(@PathVariable("tutorId") Long tutorId) {
        return conversationDetailsFacade.getTutorConversationDetails(tutorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ConversationResponseDto saveConversation(@RequestParam("conv-details-id") Long convDetailsId,
                                                    Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();

        return conversationDetailsFacade.saveConversation(convDetailsId, userId);
    }

    @GetMapping("/students")
    public List<ConversationResponseDto> findAllByStudentId(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();

        return conversationDetailsFacade.findAllByStudentId(userId);
    }

    @GetMapping("/tutors")
    public List<ConversationResponseDto> findAllByTutorId(Authentication authentication) {
        Long userId = ((PrincipalUser) authentication.getPrincipal()).getUserId();

        return conversationDetailsFacade.findAllByTutorId(userId);
    }
}
