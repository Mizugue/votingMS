package com.hallak.PollManagerService.services;

import com.hallak.PollManagerService.entities.PollDTO;
import org.springframework.stereotype.Service;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    public PollDTO newPoll(PollDTO pollDTO){
        return pollDTO;

    }




}
