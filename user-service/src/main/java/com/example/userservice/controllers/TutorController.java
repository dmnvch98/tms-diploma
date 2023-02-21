package com.example.userservice.controllers;

import com.example.userservice.facades.TutorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tutors")
public class TutorController {
    private final TutorFacade tutorFacade;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteTutor(@PathVariable("userId") Long userId) {
        tutorFacade.deleteTutor(userId);
    }
}
