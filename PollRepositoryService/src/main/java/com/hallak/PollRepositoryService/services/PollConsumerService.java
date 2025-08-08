package com.hallak.PollRepositoryService.services;


import com.hallak.sharedDtos.dtos.BallotDTO;
import com.hallak.sharedDtos.dtos.BallotResponseDTO;
import com.hallak.sharedDtos.dtos.PollDTO;


public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);
    BallotResponseDTO receiveBallot(BallotDTO ballotDTO);

}
