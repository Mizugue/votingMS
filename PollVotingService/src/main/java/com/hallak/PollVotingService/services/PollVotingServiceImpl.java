package com.hallak.PollVotingService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.PollVotingService.OPF.PollRepositoryClient;
import com.hallak.shared_library.dtos.*;
import com.hallak.shared_library.errorhandling.APIError;
import com.hallak.shared_library.exceptions.AsyncError;
import com.hallak.shared_library.exceptions.AsyncErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PollVotingServiceImpl implements PollVotingService{

    private final PollRepositoryClient pollRepositoryClient;
    private final RabbitTemplate rabbitTemplate;
    private final String ballotsQueueName;
    private static final Logger log = LoggerFactory.getLogger(PollVotingServiceImpl.class);
    private final ObjectMapper objectMapper;


    @Autowired
    public PollVotingServiceImpl(@Value("${rabbitmq.queues.ballots}") String ballotsQueueName, PollRepositoryClient pollRepositoryClient,
                                  RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.pollRepositoryClient = pollRepositoryClient;
        this.rabbitTemplate = rabbitTemplate;
        this.ballotsQueueName = ballotsQueueName;
        this.objectMapper = objectMapper;
    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepositoryClient.findAllPolls();
    }

    @Override
    public BallotResponseDTO validateBallot(BallotDTO ballotDTO) {
        Object response = rabbitTemplate.convertSendAndReceive(ballotsQueueName, ballotDTO);
        log.info("Response class: {}", response != null ? response.getClass().getName() : "null");

        if (response instanceof LinkedHashMap) { // Sou obrigado a fazer isso, justo que a implmentacao padrao do Jackson para representar JSON de objeto e LinkedHaskMAp
            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) response;

            if (map.containsKey("error")) {
                AsyncError asyncError = objectMapper.convertValue(map, AsyncError.class);
                throw new AsyncErrorException(asyncError);
            }

            if (map.containsKey("pollId")){
                return objectMapper.convertValue(map, BallotResponseDTO.class);
            }

            throw new RuntimeException("Unknown response map structure");
        }

        throw new RuntimeException("Unexpected response type: " + (response != null ? response.getClass() : "null"));
    }

    @Override
    public PersonResponseDTO newPerson(PersonDTO personDTO) {
        return pollRepositoryClient.newPerson(personDTO);
    }


}
