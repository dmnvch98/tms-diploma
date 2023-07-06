package com.example.apigateway.facades;

import com.example.apigateway.converters.FeedbackConverter;
import com.example.apigateway.dto.*;
import com.example.apigateway.model.Feedback;
import com.example.apigateway.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class FeedbackFacade {
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final ConversationDetailsService conversationDetailsService;
    private final FileService fileService;
    private final LanguageLevelService languageLevelService;
    private final FeedbackConverter feedbackConverter;
    @Value("${avatar.user_postfix}")
    public String userAvatarNamePostfix;

    public Feedback saveFeedbackAboutStudent(FeedbackAboutStudentRequestDto dto, Long userId) {
        Long tutorId = userService.get(userId).getTutor().getTutorId();
        return conversationDetailsService
            .countAllByConversationDetailsIdAndTutorId(dto.getConversationId(), tutorId)
            ? feedbackService.saveFeedbackAboutStudent(dto)
            : null;
    }

    public Feedback saveFeedbackAboutTutor(FeedbackAboutTutorRequestDto dto, Long userId) {
        Long studentId = userService.get(userId).getStudent().getStudentId();
        return conversationDetailsService
            .countAllByConvIdAndStudentId(dto.getConversationId(), studentId)
            ? feedbackService.saveFeedbackAboutTutor(dto)
            : null;
    }

    public List<FeedbackCardInfoDto> findFeedbacksAboutTutor(Long tutorId) {
        return feedbackService
            .findFeedbacksAboutTutor(tutorId)
            .stream()
            .map(this::buildFeedbackCardInfoFromFeedbackAboutTutor)
            .toList();
    }

    public List<FeedbackCardInfoDto> findFeedbacksAboutStudent(Long studentId) {
        return feedbackService
            .findFeedbacksAboutStudent(studentId)
            .stream()
            .map(this::buildFeedbackCardInfoFromFeedbackAboutStudent)
            .toList();
    }

    private FeedbackCardInfoDto buildFeedbackCardInfoFromFeedbackAboutTutor(FeedbackAboutTutorResponseDto dto) {
        UserResponseDto userResponseDto = userService
            .findUserByStudentId(dto.getStudentId());

        Map<String, String> userInfo = getAdditionalInfoForFeedbackCard(userResponseDto, dto.getLanguageId());

        return feedbackConverter.feedbackAboutTutorResponseDtoToCardDto(
            dto,
            userInfo.get("languageDescription"),
            userInfo.get("avatarUrl"),
            userInfo.get("firstName"),
            userInfo.get("lastname"));
    }

    private FeedbackCardInfoDto buildFeedbackCardInfoFromFeedbackAboutStudent(FeedbackAboutStudentResponseDto dto) {
        UserResponseDto userResponseDto = userService
            .findUserByTutorId(dto.getTutorId());

        Map<String, String> userInfo = getAdditionalInfoForFeedbackCard(userResponseDto, dto.getLanguageId());

        return feedbackConverter.feedbackAboutStudentResponseDtoToCardDto(
            dto,
            userInfo.get("languageDescription"),
            userInfo.get("avatarUrl"),
            userInfo.get("firstName"),
            userInfo.get("lastname"));
    }

    private Map<String, String> getAdditionalInfoForFeedbackCard(UserResponseDto user, Long languageId) {
        Map<String, String> userInfo = new HashMap<>();

        String avatarUrl = fileService
            .getAvatarUrl(user.getId())
            .getFileName();

        String languageDescription = languageLevelService
            .findAllByLanguageId(languageId)
            .getDescription();

        String firstName = user.getFirstName();

        String lastname = user.getLastName();

        userInfo.put("avatarUrl", avatarUrl);
        userInfo.put("languageDescription", languageDescription);
        userInfo.put("firstName", firstName);
        userInfo.put("lastname", lastname);

        return userInfo;
    }

}
