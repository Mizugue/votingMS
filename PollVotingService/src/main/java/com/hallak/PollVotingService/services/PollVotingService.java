package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.dtos.BallotDTO;
import com.hallak.PollVotingService.dtos.PersonDTO;
import com.hallak.PollVotingService.dtos.PollDTO;

import java.util.List;

public interface PollVotingService {
    List<PollDTO> findAllPolls();
    BallotDTO validateBallot(BallotDTO ballotDTO);
    PersonDTO newPerson(PersonDTO personDTO);
}
