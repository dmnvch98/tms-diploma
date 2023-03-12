package com.example.apigateway.facades;

import com.example.apigateway.converters.UserConverter;
import com.example.apigateway.dto.FilterTutorsRequestDto;
import com.example.apigateway.dto.TutorCardInfoDto;
import com.example.apigateway.dto.TutorCardsResponseDto;
import com.example.apigateway.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ConversationDetailsFacade {

    private final ConversationDetailsService conversationDetailsService;
    private final AddressService addressService;
    private final FileService fileService;
    private final UserConverter userConverter;
    private final TutorService tutorService;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public TutorCardsResponseDto findTutorCardInfoWithMinPrice(Long lastTutorId) {
        List<TutorCardInfoDto> tutors = tutorService.findTutorsWithExistingConvDetails(lastTutorId)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                conversationDetailsService.findTutorMinimumPrice(tutor.getTutorId()),
                addressService.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getFile(tutor.getUserId() + userAvatarNamePostfix).getMessage()))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countAllTutorsWithConvDetails())
            .build();
    }

    public TutorCardsResponseDto filterTutors(Long lastTutorId, FilterTutorsRequestDto dto) {
        List<TutorCardInfoDto> tutors = tutorService.filterTutors(lastTutorId, dto)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                conversationDetailsService.findTutorMinimumPrice(dto, tutor.getTutorId()),
                addressService.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getFile(tutor.getUserId() + userAvatarNamePostfix).getMessage()))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countFilteredTutorsWithConvDetails(dto))
            .build();
    }
}