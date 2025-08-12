package com.hallak.PollManagerService.services;


import com.hallak.PollManagerService.dtos.PollDTOFromManager;
import com.hallak.shared_library.dtos.PollDTO;

public interface PollManagerService {
    PollDTO newPoll(PollDTOFromManager pollDTO);


}
