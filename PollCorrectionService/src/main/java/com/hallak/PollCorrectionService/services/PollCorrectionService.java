package com.hallak.PollCorrectionService.services;

import com.hallak.PollCorrectionService.dto.BallotDTO;
import com.hallak.PollCorrectionService.dto.VoteStatistics;
import com.hallak.PollCorrectionService.dto.VoteSummary;

public interface PollCorrectionService {
    VoteStatistics getWinnerByPollId(Long id);


}
