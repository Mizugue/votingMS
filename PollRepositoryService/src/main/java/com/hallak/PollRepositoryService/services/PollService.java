package com.hallak.PollRepositoryService.services;



import com.hallak.shared_library.dtos.BallotToCorrectionDTO;
import com.hallak.shared_library.dtos.PollDTO;

import java.util.List;

public interface PollService {
    List<PollDTO> findAllPolls();
    List<BallotToCorrectionDTO> findAllBallotsByPollId(Long pollId);
    String findPollNameByPollId(Long pollId);

}
