package com.example.userservice.facades;

import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.TutorCardInfo;
import com.example.userservice.exceptions.TutorCannotBeDeletedException;
import com.example.userservice.model.User;
import com.example.userservice.services.TutorService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;
    private final UserService userService;
    private final UserConverter userConverter;
    private final UserFacade userFacade;

    public String deleteTutor(Long userId) {
        User user = userService.get(userId);
        if (user.getStudent() != null) {
            tutorService.deleteTutor(userId);
            return null;
        } else {
            throw new TutorCannotBeDeletedException();
        }
    }

    public List<TutorCardInfo> findTutorsWithExistingConvDetails() {
        return userService.findTutorsWithExistingConvDetails()
            .stream()
            .map(user -> userConverter.userToTutorCardInfo(user, userFacade.findLanguageLevelsByUserId(user.getId())))
            .toList();
    }
}
