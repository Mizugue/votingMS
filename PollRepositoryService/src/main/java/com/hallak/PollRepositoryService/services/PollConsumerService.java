package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;

public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);
    void receiveBallot(BallotDTO ballotDTO);


}
