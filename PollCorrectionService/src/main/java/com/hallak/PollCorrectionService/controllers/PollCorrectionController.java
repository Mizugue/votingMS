package com.hallak.PollCorrectionService.controllers;

import com.hallak.PollCorrectionService.dto.VoteStatistics;
import com.hallak.PollCorrectionService.services.PollCorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollCorrectionController {

    private final PollCorrectionService pollCorrectionService;


    @Autowired
    public PollCorrectionController(PollCorrectionService pollCorrectionService) {
        this.pollCorrectionService = pollCorrectionService;
    }

    @GetMapping(value = "/{pollId}")
    public ResponseEntity<VoteStatistics> getWinnerByPollId(@PathVariable Long pollId){
        return new ResponseEntity<>(pollCorrectionService.getWinnerByPollId(pollId), HttpStatus.OK);
    }



}
