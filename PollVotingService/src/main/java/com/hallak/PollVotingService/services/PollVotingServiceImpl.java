package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.OPFConfig.PollRepositoryClient;
import com.hallak.PollVotingService.dtos.BallotDTO;
import com.hallak.PollVotingService.dtos.PollDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollVotingServiceImpl implements PollVotingService{

    private final PollRepositoryClient pollRepositoryClient;
    private final Queue queueToSaveNewBallots;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public PollVotingServiceImpl(PollRepositoryClient pollRepositoryClient, Queue queueToSaveNewBallots, RabbitTemplate rabbitTemplate) {
        this.pollRepositoryClient = pollRepositoryClient;
        this.rabbitTemplate = rabbitTemplate;
        this.queueToSaveNewBallots = queueToSaveNewBallots;
    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepositoryClient.findAllPolls();
    }

    @Override
    public BallotDTO validateBallot(BallotDTO ballotDTO) {
        rabbitTemplate.convertAndSend(queueToSaveNewBallots.getName(), ballotDTO);
        return ballotDTO;
    }



}
