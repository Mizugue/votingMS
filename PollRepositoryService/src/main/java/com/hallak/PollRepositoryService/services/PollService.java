package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;

import java.util.List;

public interface PollService {
    List<PollDTO> findAllPolls();
    List<BallotToCorrectionDTO> findAllBallotsByPollId(Long pollId);
    String findPollNameByPollId(Long pollId);

}
