package com.example.apigateway.controllers;

import com.example.apigateway.dto.FilterTutorsRequestDto;
import com.example.apigateway.dto.TutorCardsResponseDto;
import com.example.apigateway.facades.ConversationDetailsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}