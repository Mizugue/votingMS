package com.hallak.PollVotingService.controllers;


import com.hallak.PollVotingService.services.PollVotingService;
import com.hallak.shared_library.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toVoting/")
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

    @PostMapping
    public ResponseEntity<BallotResponseDTO> validateBallot(@RequestBody BallotDTO ballotDTO){
        return new ResponseEntity<>(pollVotingService.validateBallot(ballotDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "newPerson")
    public ResponseEntity<PersonResponseDTO> newPerson(@RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(pollVotingService.newPerson(personDTO), HttpStatus.CREATED);
    }








}
