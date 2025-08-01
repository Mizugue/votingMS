package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.PollDTO;

public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);


}
