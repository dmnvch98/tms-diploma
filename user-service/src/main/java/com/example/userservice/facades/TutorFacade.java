package com.example.userservice.facades;

import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.FilterTutorsRequestDto;
import com.example.userservice.dto.TutorShortUserInfoDto;
import com.example.userservice.exceptions.TutorCannotBeDeletedException;
import com.example.userservice.model.Tutor;
import com.example.userservice.model.User;
import com.example.userservice.services.FeedbackService;
import com.example.userservice.services.TutorService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TutorFacade {
    private final UserService userService;
    private final UserConverter userConverter;
    private final UserFacade userFacade;
    private final FeedbackService feedbackService;
    private final TutorService tutorService;

    @Value("${roles.tutor_role_name}")
    String tutorRoleName;

    public void deleteTutor(Long userId) {
        User user = userService.get(userId);
        if (user.getStudent() != null) {
            userService.deleteRoleFromUser(tutorRoleName, userId);
            tutorService.deleteByUserId(userId);
        } else {
            throw new TutorCannotBeDeletedException();
        }
    }

    public List<TutorShortUserInfoDto> findTutorsWhoHaveNotBookedConvDetails(Long lastTutorId) {
        return userService.findTutorsWhoHaveNotBookedConvDetails(lastTutorId)
            .stream()
            .map(user -> userConverter.userToTutorCardInfo(
                user,
                userFacade.findLanguageLevelsByUserId(user.getId()),
                feedbackService.findAvgRateForTutor(userFacade.getTutorIdIfExists(user))))
            .toList();
    }

    public Tutor save(Tutor tutor) {
        userService.addRoleToUser(tutorRoleName, tutor.getUserId());
        return tutorService.save(tutor);
    }

    public List<TutorShortUserInfoDto> filterTutorsWhoHaveNotBookedConvDetails(Long lastTutorId, FilterTutorsRequestDto dto) {
        return userService
            .filterTutorsWhoHaveNotBookedConvDetails(lastTutorId, dto.getMinPrice(), dto.getMaxPrice(),
                dto.getCity(), dto.getCountryId(), dto.getConversationTypeId(), dto.getLevelId(), dto.getLanguageId())
            .stream()
            .map(user -> userConverter.userToTutorCardInfo(
                user,
                userFacade.findLanguageLevelsByUserId(user.getId()),
                feedbackService.findAvgRateForTutor(userFacade.getTutorIdIfExists(user))
            ))
            .toList();
    }
}
