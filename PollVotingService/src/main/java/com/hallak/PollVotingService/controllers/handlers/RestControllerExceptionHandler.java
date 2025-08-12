package com.hallak.PollVotingService.controllers.handlers;

import com.hallak.shared_library.errorhandling.APIError;
import com.hallak.shared_library.exceptions.AsyncError;
import com.hallak.shared_library.exceptions.AsyncErrorException;
import com.hallak.shared_library.exceptions.EntityAlreadyExistsException;
import com.hallak.shared_library.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RestControllerExceptionHandler {



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> resourceNotFound(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getApiError());
    }



    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<APIError> EntityExists(EntityAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getApiError());
    }

    @ExceptionHandler(AsyncErrorException.class)
    public ResponseEntity<AsyncError> AsyncError(AsyncErrorException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getAsyncError());
    }


}






