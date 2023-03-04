package com.example.convservice.facades;

import com.example.convservice.converters.ConversationConverter;
import com.example.convservice.converters.UserConverter;
import com.example.convservice.dto.*;
import com.example.convservice.model.ConversationDetails;
import com.example.convservice.model.User;
import com.example.convservice.services.ConversationDetailsService;
import com.example.convservice.services.FileService;
import com.example.convservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConversationDetailsFacade {
    private final ConversationDetailsService service;
    private final UserService userService;
    private final ConversationConverter converter;
    private final UserConverter userConverter;
    private final AddressFacade addressFacade;
    private final FileService fileService;

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
        List<TutorCardInfo> list = userService.findTutorsWithExistingConvDetails();
        List<TutorCardInfoMinPrice> list2 = new ArrayList<>();
        for (TutorCardInfo tci: list) {
            double price = findMinimumPriceByTutorId(tci.getTutorId());
            List<AddressDto> addressDtos = addressFacade.findAddressesDistinctByTutorId(tci.getTutorId());
            String url = fileService.getAvatarUrl(tci.getAvatarName());
            list2.add(userConverter.tutorCardInfoToMinPrice(tci, price, addressDtos, url));
        }
        return list2;
//        return userService.findTutorsWithExistingConvDetails()
//            .stream()
//            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
//                tutor,
//                findMinimumPriceByTutorId(tutor.getTutorId()),
//                addressFacade.findAddressesDistinctByTutorId(tutor.getTutorId()),
//                fileService.getAvatarUrl(tutor.getAvatarName())))
//            .toList();
    }
}
