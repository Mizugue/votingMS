package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.OPFConfig.PollRepositoryClient;
import com.hallak.PollVotingService.dtos.BallotDTO;
import com.hallak.PollVotingService.dtos.PollDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollVotingServiceImpl implements PollVotingService{

    private final PollRepositoryClient pollRepositoryClient;

    @Autowired
    public PollVotingServiceImpl(PollRepositoryClient pollRepositoryClient) {
        this.pollRepositoryClient = pollRepositoryClient;
    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepositoryClient.findAllPolls();
    }

    @Override
    public BallotDTO validateBallot(BallotDTO ballotDTO) {
        return pollRepositoryClient.validateBallot(ballotDTO);

    }



}
