package com.hallak.PollManagerService.controllers.handlers;

import com.hallak.shared_library.exceptions.AsyncError;
import com.hallak.shared_library.exceptions.AsyncErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {


    @ExceptionHandler(AsyncErrorException.class)
    public ResponseEntity<AsyncError> AsyncError(AsyncErrorException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getAsyncError());
    }

}
