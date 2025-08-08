package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.OPF.PollRepositoryClient;
import com.hallak.shared_library.dtos.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollVotingServiceImpl implements PollVotingService{

    private final PollRepositoryClient pollRepositoryClient;
    private final RabbitTemplate rabbitTemplate;
    private final String ballotsQueueName;


    @Autowired
    public PollVotingServiceImpl(@Value("${rabbitmq.queues.ballots}") String ballotsQueueName, PollRepositoryClient pollRepositoryClient,
                                  RabbitTemplate rabbitTemplate) {
        this.pollRepositoryClient = pollRepositoryClient;
        this.rabbitTemplate = rabbitTemplate;
        this.ballotsQueueName = ballotsQueueName;
    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepositoryClient.findAllPolls();
    }

    @Override
    public BallotResponseDTO validateBallot(BallotDTO ballotDTO) {
        return (BallotResponseDTO) rabbitTemplate.convertSendAndReceive(ballotsQueueName, ballotDTO);

    }

    @Override
    public PersonResponseDTO newPerson(PersonDTO personDTO) {
        return pollRepositoryClient.newPerson(personDTO);
    }


}
