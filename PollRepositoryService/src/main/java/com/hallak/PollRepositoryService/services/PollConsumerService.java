package com.hallak.PollRepositoryService.services;


import com.hallak.shared_library.dtos.BallotDTO;
import com.hallak.shared_library.dtos.BallotResponseDTO;
import com.hallak.shared_library.dtos.PollDTO;

public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);
    BallotResponseDTO receiveBallot(BallotDTO ballotDTO);

}
