package com.example.apigateway.controllers;

import com.example.apigateway.config.security.service.PrincipalUser;
import com.example.apigateway.services.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tutors")
public class TutorController {
    private final TutorService tutorService;

    @DeleteMapping
    public ResponseEntity<?> deleteTutor(Authentication authentication) {
        return ResponseEntity.ok(tutorService.deleteTutor(
            ((PrincipalUser) authentication.getPrincipal()).getUserId()));
    }
}
