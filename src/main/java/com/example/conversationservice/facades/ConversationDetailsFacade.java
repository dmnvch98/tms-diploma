package com.example.conversationservice.facades;

import com.example.conversationservice.converters.ConversationConverter;
import com.example.conversationservice.converters.UserConverter;
import com.example.conversationservice.dto.ConversationDetailsRequestDto;
import com.example.conversationservice.dto.ConversationDetailsResponseDto;
import com.example.conversationservice.dto.FilterTutorsRequestDto;
import com.example.conversationservice.dto.TutorCardInfoMinPrice;
import com.example.conversationservice.model.ConversationDetails;
import com.example.conversationservice.model.User;
import com.example.conversationservice.services.ConversationDetailsService;
import com.example.conversationservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationDetailsFacade {
    private final ConversationDetailsService service;
    private final UserService userService;
    private final ConversationConverter converter;
    private final UserConverter userConverter;
    private final AddressFacade addressFacade;

    public ConversationDetailsResponseDto save(ConversationDetailsRequestDto conversationDetailsDto) {
        ConversationDetails conversationDetails =
            service.save(converter.dtoToConversationDetails(conversationDetailsDto));
        return converter.conversationDetailsToResponseDto(conversationDetails);
    }

    public List<ConversationDetailsResponseDto> findAllByTutorId(Long tutorId) {
        return service.findAllByTutorId(tutorId).stream()
            .map(converter::conversationDetailsToResponseDto)
            .toList();
    }

    public List<User> filterTutors(FilterTutorsRequestDto dto) {
        return service.filterTutors(dto.getMinPrice(), dto.getMaxPrice(), dto.getConversationTypeId(), dto.getLocation(),
            dto.getLanguageId(), dto.getLevelId());
    }

    public double findMinimumPriceByTutorId(Long tutorId) {
        return service.findMinimumPriceByUserId(tutorId);
    }

    public List<TutorCardInfoMinPrice> findTutorCardInfoWithMinPrice() {
        return userService.findTutorsWithExistingConvDetails()
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                findMinimumPriceByTutorId(tutor.getTutorId()),
                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId())))
            .toList();
    }

    public List<User> filterTutors(double price, Long convTypeId,
                                   String location, Long languageId, Long levelId) {
        return service.filterTutors(price, convTypeId, location, languageId, levelId);
    }

    public List<User> filterTutors(double price, Long convTypeId,
                                   String location, Long languageId, Long levelId) {
        return service.filterTutors(price, convTypeId, location, languageId, levelId);
    }
}
