package com.hallak.shared_library.errorhandling;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class SharedControllerExceptionHandler {

    @ExceptionHandler(com.hallak.sharedDtos.dtos.toException.ResourceNotFoundException.class)
    public ResponseEntity<com.hallak.sharedDtos.dtos.toException.APIError> resourceNotFound(com.hallak.sharedDtos.dtos.toException.ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new com.hallak.sharedDtos.dtos.toException.APIError
                        (Instant.now()
                                ,HttpStatus.NOT_FOUND.value()
                                ,e.getMessage()
                                ,request.getRequestURI()));
    }
}


