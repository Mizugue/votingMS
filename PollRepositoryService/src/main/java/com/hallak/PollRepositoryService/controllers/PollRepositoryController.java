package com.hallak.PollRepositoryService.controllers;

import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PersonDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.services.PersonService;
import com.hallak.PollRepositoryService.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class PollRepositoryController {


    private final PollService pollService;
    private final PersonService personService;




    @Autowired
    public PollRepositoryController(PollService pollService, PersonService personService) {
        this.pollService = pollService;
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PollDTO>> findAllPolls(){
        return new ResponseEntity<>(pollService.findAllPolls(), HttpStatus.OK);
    }

    @GetMapping(value = "ballots/{pollId}")
    public ResponseEntity<List<BallotToCorrectionDTO>> findAllBallotsByPollId(@PathVariable Long pollId){
        return new ResponseEntity<>(pollService.findAllBallotsByPollId(pollId), HttpStatus.OK);
    }

    @GetMapping(value = "ballots/pollName/{pollId}")
    public ResponseEntity<String> findPollNameByPollId(@PathVariable Long pollId){
        return new ResponseEntity<>(pollService.findPollNameByPollId(pollId), HttpStatus.OK);
    }

    @PostMapping(value = "newPerson")
    public ResponseEntity<PersonResponseDTO> newPerson(@RequestBody PersonDTO personDTO){
        return new ResponseEntity<>(personService.newPerson(personDTO), HttpStatus.CREATED);
    }








}
