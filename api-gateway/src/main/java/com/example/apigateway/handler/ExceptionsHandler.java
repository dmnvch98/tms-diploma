package com.example.apigateway.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e) {
        return ResponseEntity.status(403).body(e.contentUTF8());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        int statusCode = ex.status();
        String responseBody = ex.contentUTF8();
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(statusCode)
            .message(extractMessage(responseBody)).build();
        return ResponseEntity.status(statusCode).body(errorResponse);
    }

    @Builder
    private record ErrorResponse(int status, String message) { }

    private static String extractMessage(String json) {
        ObjectMapper mapper = new ObjectMapper();
        String message = null;
        try {
            JsonNode jsonNode = mapper.readTree(json);
            message = jsonNode.get("message").asText();
        } catch (Exception e) {
            log.error("Error during parsing the response from microservice: {}", e.getMessage());
        }
        return message;
    }
}
