package com.example.convservice.controllers;

import com.example.convservice.dto.*;
import com.example.convservice.facades.ConversationDetailsFacade;
import com.example.convservice.facades.ConversationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public TutorCardsResponseDto findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId) {
        return conversationDetailsFacade.findTutorCardInfoWithMinPrice(lastTutorId);
    }

    @CrossOrigin
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
}
