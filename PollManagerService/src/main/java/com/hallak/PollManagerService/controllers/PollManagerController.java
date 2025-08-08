package com.hallak.PollManagerService.controllers;


import com.hallak.PollManagerService.services.PollManagerService;
import com.hallak.shared_library.dtos.PollDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/poll/")
public class PollManagerController {

    @Autowired
    private PollManagerService pollManagerService;


    @PostMapping
    public ResponseEntity<PollDTO> newPoll(@RequestBody PollDTO pollDTO){
        return new ResponseEntity<>(pollManagerService.newPoll(pollDTO), HttpStatus.CREATED);
    }

}
