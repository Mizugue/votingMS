package com.hallak.PollCorrectionService.controllers;

import com.hallak.PollCorrectionService.dto.BallotDTO;
import com.hallak.PollCorrectionService.dto.VoteSummary;
import com.hallak.PollCorrectionService.services.PollCorrectionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollCorrectionController {

    private PollCorrectionService pollCorrectionService;


    @Autowired
    public PollCorrectionController(PollCorrectionService pollCorrectionService) {
        this.pollCorrectionService = pollCorrectionService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VoteSummary> getWinnerByPollId(@PathVariable Long id){
        return new ResponseEntity<>(pollCorrectionService.getWinnerByPollId(id), HttpStatus.OK);
    }



}
