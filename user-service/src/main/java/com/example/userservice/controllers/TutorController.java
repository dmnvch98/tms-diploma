package com.example.userservice.controllers;

import com.example.userservice.dto.TutorCardInfo;
import com.example.userservice.facades.TutorFacade;
import com.example.userservice.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Tutor save(@RequestBody Tutor tutor) {
        return tutorFacade.save(tutor);
    }

    @GetMapping("/existing-conversations-details")
    List<TutorCardInfo> findTutorsWithExistingConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId) {
        return tutorFacade.findTutorsWithExistingConvDetails(lastTutorId);
    }
}
