package com.hallak.PollRepositoryService.services;


import com.hallak.PollRepositoryService.entities.Ballot;
import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.entities.Poll;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import com.hallak.PollRepositoryService.security.JwtUtil;
import com.hallak.shared_library.dtos.BallotDTO;
import com.hallak.shared_library.dtos.BallotResponseDTO;
import com.hallak.shared_library.dtos.PollDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollConsumerServiceImpl implements PollConsumerService {


    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;
    private final PersonRepository personRepository;
    private final BallotRepository ballotRepository;
    private static final Logger log = LoggerFactory.getLogger(PollConsumerServiceImpl.class);

    @Autowired
    public PollConsumerServiceImpl(JwtUtil jwtUtil, PersonService personService, BallotRepository ballotRepository, PersonRepository personRepository, ModelMapper modelMapper, PollRepository pollRepository) {
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
        this.ballotRepository = ballotRepository;
        this.personRepository = personRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.polls}")
    @Transactional
    public void receivePoll(@Payload PollDTO pollDTO){
        log.info("Received {}", pollDTO);
        pollRepository.save(modelMapper.map(pollDTO, Poll.class));
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.ballots}")
    @Transactional
    public BallotResponseDTO receiveBallot(BallotDTO ballotDTO) {
         Optional<Poll> poll = pollRepository.findById(ballotDTO.getPollId());
         if (poll.isEmpty()){
             log.error("This poll doesn't exists");
             throw new EntityNotFoundException("This poll doesn't exists");
         }


        String cpfOfBallot = jwtUtil.validateTokenAndGetCpf(ballotDTO.getToken());
        Optional<Person> person = personRepository.findByCpf(cpfOfBallot);
        if (person.isEmpty()){
            log.error("This person doesn't exists");
            throw new EntityNotFoundException("This person doesn't exists");
        }

        boolean existsBallot = ballotRepository.existsByVoterIdAndPollId(
                person.get().getId(), ballotDTO.getPollId());
        if (existsBallot){
            log.error("This person already did a ballot in this poll");
            throw new EntityExistsException("This person already did a ballot in this poll");
        }

        ballotRepository.save(new Ballot(
                ballotDTO.getPollId(),
                person.get().getId(),
                ballotDTO.getOption()));

        log.info("Ballot saved successfully for person {} in poll {}", person.get().getId(), ballotDTO.getPollId());

        return new BallotResponseDTO(ballotDTO.getPollId(), ballotDTO.getOption(), cpfOfBallot);
    }











}