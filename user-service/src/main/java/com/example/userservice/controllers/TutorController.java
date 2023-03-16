package com.example.userservice.controllers;

import com.example.userservice.dto.FilterTutorsRequestDto;
import com.example.userservice.dto.TutorShortUserInfoDto;
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

    @GetMapping("/not-booked-conversations-details")
    List<TutorShortUserInfoDto> findTutorsWhoHaveNotBookedConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId) {
        return tutorFacade.findTutorsWhoHaveNotBookedConvDetails(lastTutorId);
    }

    @GetMapping("/not-booked-conversations-details/filter")
    public List<TutorShortUserInfoDto> filterTutorsWhoHaveNotBookedConvDetails(@RequestParam(value = "lastTutorId",
        defaultValue = "0", required = false) Long lastTutorId, @RequestBody FilterTutorsRequestDto dto) {
        return tutorFacade.filterTutorsWhoHaveNotBookedConvDetails(lastTutorId, dto);
    }
}
