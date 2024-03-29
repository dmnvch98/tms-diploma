package com.example.apigateway.facades;

import com.example.apigateway.converters.UserConverter;
import com.example.apigateway.dto.*;
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
    private final UserService userService;

    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public TutorCardsResponseDto findTutorCardInfoWithMinPrice(Long lastTutorId) {
        List<TutorCardInfoDto> tutors = tutorService.findTutorsWhoHaveNotBookedConvDetails(lastTutorId)
            .stream()
            .map(tutor -> userConverter.tutorCardInfoToMinPrice(
                tutor,
                conversationDetailsService.findTutorMinimumPrice(tutor.getTutorId()),
                addressService.findAddressesDistinctByTutorId(tutor.getTutorId()),
                fileService.getAvatarUrl(tutor.getUserId()).getFileUrl()))
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
                fileService.getAvatarUrl(tutor.getUserId()).getFileName()))
            .toList();

        return TutorCardsResponseDto
            .builder()
            .tutors(tutors)
            .totalCount(conversationDetailsService.countFilteredTutorsWithConvDetails(dto))
            .build();
    }

    public ConversationDetailsResponseDto saveConversationDetails(ConversationDetailsRequestDto dto, Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        dto.setTutorId(tutorId);
        return conversationDetailsService.saveConversationDetails(dto);
    }

    public List<ConversationDetailsResponseDto> getTutorConversationDetails(Long tutorId) {
        return conversationDetailsService.getTutorConversationDetails(tutorId);
    }

    public ConversationResponseDto saveConversation(Long convDetailsId, Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        ConversationRequestDto dto = ConversationRequestDto
                .builder()
                .conversationDetailsId(convDetailsId)
                .studentId(studentId)
                .build();
        return conversationDetailsService.saveConversation(dto);
    }

    public List<ConversationResponseDto> findAllByStudentId(Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return conversationDetailsService.findAllByStudentId(studentId);
    }

    public List<ConversationResponseDto> findAllByTutorId(Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return conversationDetailsService.findAllByTutorId(tutorId);
    }
}
