package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.converters.UserConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.services.ConversationDetailsService;
import com.example.convservice.services.FileService;
import com.example.convservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

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
        List<TutorCardInfoDto> tutors = userService.filterTutors(tutorId, dto)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                findMinimumPrice(tutor.getTutorId(), dto.getConversationTypeId(),
                    dto.getLevelId(), dto.getLanguageId()),
                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getAvatarUrl(tutor.getUserId() + userAvatarNamePostfix)))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countFilteredTutorsWithConvDetails(dto.getMinPrice(),
                dto.getMaxPrice(),dto.getCity(), dto.getCountryId(), dto.getConversationTypeId(),
                dto.getLevelId(), dto.getLanguageId()))
            .build();
    }

    public double findMinimumPrice(Long tutorId) {
        return conversationDetailsService.findMinimumPriceByUserId(tutorId);
    }

    public double findMinimumPrice(Long tutorId, Long convTypeId, Long minLevelId, Long languageId ) {
        return conversationDetailsService.findMinimumPriceByUserId(tutorId, convTypeId, minLevelId, languageId);
    }

    public TutorCardsResponseDto findTutorCardInfoWithMinPrice(Long lastTutorId) {
        List<TutorCardInfoDto> tutors = userService.findTutorsWithExistingConvDetails(lastTutorId)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                findMinimumPrice(tutor.getTutorId()),
                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getAvatarUrl(tutor.getUserId() + userAvatarNamePostfix)))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countAllTutorsWithConvDetails())
            .build();
    }

}
