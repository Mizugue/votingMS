package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;

import java.util.List;

public interface PollService {
    List<PollDTO> findAllPolls();
    List<BallotToCorrectionDTO> findAllBallots();

}
