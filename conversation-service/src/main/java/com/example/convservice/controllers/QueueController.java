package com.example.convservice.controllers;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/queue")
@Slf4j
public class QueueController {
    private final AmazonSQS amazonSQS;

    @PostMapping("/{msg}")
    public void putMessagedToQueue(@PathVariable("msg") String message) {
        SendMessageRequest messageRequest = new SendMessageRequest("http://localhost:4566/000000000000/status-update", message);
        messageRequest.setDelaySeconds(10);
        amazonSQS.sendMessage(messageRequest);
    }

}
