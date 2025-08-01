package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.PollDTO;

import java.util.List;

public interface PollService {
    List<PollDTO> findAllPolls();

}
