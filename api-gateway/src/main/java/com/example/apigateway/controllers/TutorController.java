package com.example.apigateway.controllers;

import com.example.apigateway.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tutors")
public class TutorController {
    private final TutorService tutorService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteTutor(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(tutorService.deleteTutor(userId));
    }
}
