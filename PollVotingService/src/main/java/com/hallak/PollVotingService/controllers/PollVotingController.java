package com.hallak.PollVotingService.controllers;

import com.hallak.PollVotingService.dtos.PollDTO;
import com.hallak.PollVotingService.services.PollVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PollVotingController {

    private final PollVotingService pollVotingService;

    @Autowired
    public PollVotingController(PollVotingService pollVotingService) {
        this.pollVotingService = pollVotingService;
    }


    @GetMapping
    public ResponseEntity<List<PollDTO>> findAllPolls(){
        return new ResponseEntity<>(pollVotingService.findAllPolls(), HttpStatus.OK);
    }






}
