package com.hallak.PollVotingService.services;


import com.hallak.shared_library.dtos.*;

import java.util.List;

public interface PollVotingService {
    List<PollDTO> findAllPolls();
    BallotResponseDTO validateBallot(BallotDTO ballotDTO);
    PersonResponseDTO newPerson(PersonDTO personDTO);
}
