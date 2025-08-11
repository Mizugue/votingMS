package com.hallak.PollRepositoryService.controllers.handlers;

import com.hallak.shared_library.errorhandling.APIError;
import com.hallak.shared_library.exceptions.EntityAlreadyExistsException;
import com.hallak.shared_library.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIError
                        (Instant.now()
                                ,HttpStatus.NOT_FOUND.value()
                                ,e.getMessage()
                                ,request.getRequestURI()));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<APIError> EntityAlreadyExists(EntityAlreadyExistsException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIError
                        (Instant.now()
                                ,HttpStatus.NOT_FOUND.value()
                                ,e.getMessage()
                                ,request.getRequestURI()));
    }
}






