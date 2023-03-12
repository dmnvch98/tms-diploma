package com.example.apigateway.facades;

import com.example.apigateway.converters.UserConverter;
import com.example.apigateway.dto.TutorCardInfoDto;
import com.example.apigateway.dto.TutorCardsResponseDto;
import com.example.apigateway.services.AddressService;
import com.example.apigateway.services.ConversationDetailsService;
import com.example.apigateway.services.FileService;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ConversationDetailsFacade {

    private final ConversationDetailsService conversationDetailsService;
    private final AddressService addressService;
    private final UserService userService;
    private final FileService fileService;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;
    private final UserConverter userConverter;

    public TutorCardsResponseDto findTutorCardInfoWithMinPrice(Long lastTutorId) {
        List<TutorCardInfoDto> tutors = userService.findTutorsWithExistingConvDetails(lastTutorId)
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
}
