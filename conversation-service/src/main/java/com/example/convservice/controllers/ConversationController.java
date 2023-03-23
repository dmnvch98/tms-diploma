package com.example.convservice.controllers;

import com.example.convservice.dto.*;
import com.example.convservice.facades.ConversationDetailsFacade;
import com.example.convservice.facades.ConversationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/conversations")
public class ConversationController {

    private final ConversationDetailsFacade conversationDetailsFacade;
    private final ConversationFacade conversationFacade;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/details")
    public ConversationDetailsResponseDto saveConversationDetails(@RequestBody ConversationDetailsRequestDto dto) {
        return conversationDetailsFacade.save(dto);
    }

    @GetMapping("/details/tutor/{tutorId}")
    public List<ConversationDetailsResponseDto> getTutorConversationDetails(@PathVariable("tutorId") Long tutorId) {
        return conversationDetailsFacade.findAllByTutorId(tutorId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ConversationResponseDto saveConversation(@RequestBody ConversationRequestDto dto) {
        return conversationFacade.save(dto);
    }

    @GetMapping("/tutors/{tutorId}/minPrice")
    public double findTutorMinimumPrice(@PathVariable("tutorId") Long tutorId) {
        return conversationDetailsFacade.findTutorMinimumPrice(tutorId);
    }

    @GetMapping("/tutors/{tutorId}/minPrice/filter")
    double findTutorMinimumPrice(@RequestBody FilterTutorsRequestDto dto, @PathVariable("tutorId") Long tutorId){
        return conversationDetailsFacade.findTutorMinimumPrice(tutorId, dto);
    }

    @GetMapping("/tutors/total")
    public int countAllTutorsWithConvDetails() {
        return conversationDetailsFacade.countAllTutorsWithConvDetails();
    }

    @GetMapping("/tutors/total/filter")
    public int countFilteredTutorsWithConvDetails(@RequestBody FilterTutorsRequestDto dto) {
        return conversationDetailsFacade.countFilteredTutorsWithConvDetails(dto);
    }

    @GetMapping("/students/{studentId}")
    public List<ConversationResponseDto> findAllByStudentId(@PathVariable("studentId") Long studentId) {
        return conversationFacade.findAllByStudentId(studentId);
    }

    @GetMapping("/tutors/{tutorId}")
    public List<ConversationResponseDto> findAllByTutorId(@PathVariable("tutorId") Long tutorId) {
        return conversationFacade.findAllByTutorId(tutorId);
    }

    @GetMapping("/{convId}/student/{studentId}/exists")
    public Boolean countAllByConvIdAndStudentId(@PathVariable("convId") Long convId,
                                                @PathVariable("studentId") Long studentId) {
        return conversationFacade.countAllByConvIdAndStudentId(convId, studentId);
    }

    @GetMapping("/{convId}/tutor/{tutorId}/exists")
    public Boolean countAllByConvIdAndTutorId(@PathVariable("convId") Long convId,
                                                             @PathVariable("tutorId") Long tutorId) {
        return conversationFacade.countAllByConvIdAndTutorId(convId, tutorId);
    }
    @GetMapping("/students/{studentId}/finished")
    public Integer countStudentLessons(@PathVariable("studentId") Long studentId) {
        return conversationFacade.countStudentLessons(studentId);
    }

    @GetMapping("/tutors/{tutorId}/finished")
    public Integer countTutorLessons(@PathVariable("tutorId")Long tutorId) {
        return conversationFacade.countTutorLessons(tutorId);
    }

}
