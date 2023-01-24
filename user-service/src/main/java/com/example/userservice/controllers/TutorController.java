package com.example.userservice.controllers;

import com.example.userservice.dto.TutorDto;
import com.example.userservice.facades.TutorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tutors")
public class TutorController {
    private final TutorFacade tutorFacade;

    @PostMapping
    public TutorDto saveTutor(@RequestBody TutorDto tutorDto) {
        return tutorFacade.saveTutor(tutorDto);
    }

    @DeleteMapping("/{userId}")
    public String deleteTutor(@PathVariable("userId") Long userId) {
        return tutorFacade.deleteTutor(userId);
    }
}
