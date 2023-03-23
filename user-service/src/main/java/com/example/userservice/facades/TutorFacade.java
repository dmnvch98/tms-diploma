package com.example.userservice.facades;

import com.example.userservice.converters.UserConverter;
import com.example.userservice.dto.TutorCardInfo;
import com.example.userservice.exceptions.TutorCannotBeDeletedException;
import com.example.userservice.model.Tutor;
import com.example.userservice.model.User;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TutorFacade {
    private final UserService userService;
    private final UserConverter userConverter;
    private final UserFacade userFacade;

    public void deleteTutor(Long userId) {
        User user = userService.get(userId);
        if (user.getStudent() != null) {
            user.getRoles().remove("Tutor");
            user.setTutor(null);
            userService.save(user);
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
    public Tutor save(Tutor tutor) {
        User user = userService.get(tutor.getUserId());
        user.setTutor(tutor);
        user.getRoles().add("Tutor");
        return userService.save(user).getTutor();
    }
}
