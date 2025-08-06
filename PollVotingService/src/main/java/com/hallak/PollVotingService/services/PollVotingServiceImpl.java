package com.hallak.PollVotingService.services;

import com.hallak.PollVotingService.OPFConfig.PollRepositoryClient;
import com.hallak.PollVotingService.dtos.BallotDTO;
import com.hallak.PollVotingService.dtos.PersonDTO;
import com.hallak.PollVotingService.dtos.PollDTO;
import org.springframework.amqp.core.Queue;
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
    private final String personsQueueName;


    @Autowired
    public PollVotingServiceImpl(@Value("${rabbitmq.queues.ballots}") String ballotsQueueName, PollRepositoryClient pollRepositoryClient,
                                 @Value("${rabbitmq.queues.persons}") String personsQueueName, RabbitTemplate rabbitTemplate) {
        this.pollRepositoryClient = pollRepositoryClient;
        this.rabbitTemplate = rabbitTemplate;
        this.ballotsQueueName = ballotsQueueName;
        this.personsQueueName = personsQueueName;
    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepositoryClient.findAllPolls();
    }

    @Override
    public BallotDTO validateBallot(BallotDTO ballotDTO) {
        rabbitTemplate.convertAndSend(ballotsQueueName, ballotDTO);
        return ballotDTO;
    }

    @Override
    public PersonDTO newPerson(PersonDTO personDTO) {
        rabbitTemplate.convertAndSend(personsQueueName, personDTO);
        return personDTO;
    }


}
