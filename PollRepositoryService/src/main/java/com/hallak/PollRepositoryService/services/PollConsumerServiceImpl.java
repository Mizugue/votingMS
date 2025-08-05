package com.hallak.PollRepositoryService.services;


import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.entities.Ballot;
import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.entities.Poll;
import com.hallak.PollRepositoryService.exceptions.ResourceNotFoundException;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PollConsumerServiceImpl implements PollConsumerService {

    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;
    private final PersonRepository personRepository;
    private final BallotRepository ballotRepository;

    @Autowired
    public PollConsumerServiceImpl(BallotRepository ballotRepository, PersonRepository personRepository, ModelMapper modelMapper, PollRepository pollRepository) {
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
        this.ballotRepository = ballotRepository;
        this.personRepository = personRepository;
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.name}")
    public void receivePoll(@Payload PollDTO pollDTO){
        System.out.println("Recebido " + pollDTO);
        pollRepository.save(modelMapper.map(pollDTO, Poll.class));
    }

    @Override
    @RabbitListener(queues = "${rabbitmq.queues.name2}")
    public void receiveBallot(@Payload BallotDTO ballotDTO) {
        System.out.println("Recebido" + ballotDTO);
        pollRepository.findById(ballotDTO.getPollId())
    .orElseThrow(() -> new EntityNotFoundException("Poll with ID " + ballotDTO.getPollId() + " not found"));
    Person person = personRepository.findByCpf(ballotDTO.getCpf()).orElseGet(() -> personRepository.save(new Person(ballotDTO.getCpf()))
    );

    Long personId = person.getId();

        boolean checkOne = ballotRepository.existsByVoterIdAndPollId(personId, ballotDTO.getPollId());

        if (checkOne){
            throw new EntityExistsException("This person already did in this poll");
        }

        boolean checkerTwo = pollRepository.existsOptionInPoll(ballotDTO.getPollId(), ballotDTO.getOption());

        if (!checkerTwo){
            throw new ResourceNotFoundException("Option '" + ballotDTO.getOption() + "' does not exist in poll");

        }

        Ballot ballot = new Ballot();
        ballot.setPollId(ballotDTO.getPollId());
        ballot.setVoterId(personId);
        ballot.setOption(ballotDTO.getOption());
        ballotRepository.save(ballot);


    }


}