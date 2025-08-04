package com.hallak.PollRepositoryService.controllers;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class PollController {


    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public ResponseEntity<List<PollDTO>> findAllPolls(){
        return new ResponseEntity<>(pollService.findAllPolls(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BallotDTO> validateBallot(@RequestBody BallotDTO ballotDTO){
        return new ResponseEntity<>(pollService.validateBallot(ballotDTO), HttpStatus.OK);
    }

    @GetMapping(value = "ballots")
    public ResponseEntity<List<BallotToCorrectionDTO>> findAllBallots(){
        return new ResponseEntity<>(pollService.findAllBallots(), HttpStatus.OK);
    }







}
