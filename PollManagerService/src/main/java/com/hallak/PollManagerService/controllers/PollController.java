package com.hallak.PollManagerService.controllers;

import com.hallak.PollManagerService.entities.PollDTO;
import com.hallak.PollManagerService.services.PollManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {

    @Autowired
    private PollManagerService pollManagerService;


    @PostMapping
    public ResponseEntity<PollDTO> newPoll(@RequestBody PollDTO pollDTO){
        return new ResponseEntity<>(pollManagerService.newPoll(pollDTO), HttpStatus.CREATED);
    }

}
