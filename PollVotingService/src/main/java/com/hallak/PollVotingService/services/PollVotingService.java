package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.dtos.PollDTO;

import java.util.List;

public interface PollVotingService {
    List<PollDTO> findAllPolls();
}
