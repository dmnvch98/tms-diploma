package com.example.userservice.facades;

import com.example.userservice.exceptions.TutorCannotBeDeletedException;
import com.example.userservice.model.User;
import com.example.userservice.services.TutorService;
import com.example.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TutorFacade {

    private final TutorService tutorService;
    private final UserService userService;
    public void deleteTutor(Long userId) {
        User user = userService.get(userId);
        if (user.getStudent() != null) {
            tutorService.deleteTutor(userId);
        } else {
            throw new TutorCannotBeDeletedException();
        }
    }
}
