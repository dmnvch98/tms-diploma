package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.converters.UserConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.services.ConversationDetailsService;
import com.example.convservice.services.FileService;
import com.example.convservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationDetailsFacade {
    private final ConversationDetailsService conversationDetailsService;
    private final UserService userService;
    private final ConversationConverter converter;
    private final UserConverter userConverter;
    private final AddressFacade addressFacade;
    private final FileService fileService;

    public ConversationDetailsResponseDto save(ConversationDetailsRequestDto conversationDetailsDto) {
        ConversationDetails conversationDetails =
            conversationDetailsService.save(converter.dtoToConversationDetails(conversationDetailsDto));
        return converter.conversationDetailsToResponseDto(conversationDetails);
    }

    public List<ConversationDetailsResponseDto> findAllByTutorId(Long tutorId) {
        return conversationDetailsService.findAllByTutorId(tutorId).stream()
            .map(converter::conversationDetailsToResponseDto)
            .toList();
    }

    public TutorCardsResponseDto filterTutors(Long tutorId, FilterTutorsRequestDto dto) {
        List<TutorCardInfoMinPrice> tutors = userService.filterTutors(tutorId, dto)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                findMinimumPriceByTutorId(tutor.getTutorId()),
                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getAvatarUrl(tutor.getAvatarName())))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countFilteredTutorsWithConvDetails(dto.getMinPrice(), dto.getMaxPrice()))
            .build();
    }

    public double findMinimumPriceByTutorId(Long tutorId) {
        return conversationDetailsService.findMinimumPriceByUserId(tutorId);
    }

    public TutorCardsResponseDto findTutorCardInfoWithMinPrice(Long lastTutorId) {
        List<TutorCardInfoMinPrice> tutors = userService.findTutorsWithExistingConvDetails(lastTutorId)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                findMinimumPriceByTutorId(tutor.getTutorId()),
                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getAvatarUrl(tutor.getAvatarName())))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countAllTutorsWithConvDetails())
            .build();
    }

    public int countAllTutorsWithConvDetails() {
        return conversationDetailsService.countAllTutorsWithConvDetails();
    }
}
