package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.dtos.*;
import com.hallak.sharedDtos.dtos.*;

import java.util.List;

public interface PollVotingService {
    List<PollDTO> findAllPolls();
    BallotResponseDTO validateBallot(BallotDTO ballotDTO);
    PersonResponseDTO newPerson(PersonDTO personDTO);
}
