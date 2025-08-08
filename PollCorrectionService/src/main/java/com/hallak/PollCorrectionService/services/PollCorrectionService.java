package com.hallak.PollCorrectionService.services;

import com.hallak.PollCorrectionService.dto.VoteStatistics;

public interface PollCorrectionService {
    VoteStatistics getWinnerByPollId(Long id);


}
