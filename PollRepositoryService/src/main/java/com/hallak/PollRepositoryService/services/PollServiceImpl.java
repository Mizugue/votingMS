package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.entities.Ballot;
import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.exceptions.ResourceNotFoundException;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService{

    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;
    private final PersonRepository personRepository;
    private final BallotRepository ballotRepository;

    @Autowired
    public PollServiceImpl(ModelMapper modelMapper, PollRepository pollRepository, PersonRepository personRepository, BallotRepository ballotRepository){
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
        this.personRepository = personRepository;
        this.ballotRepository = ballotRepository;

    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepository.findAll().stream().
                map(x -> modelMapper.map(x, PollDTO.class)).toList();
    }

    @Override
    public BallotDTO validateBallot(BallotDTO ballotDTO) {
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

        return ballotDTO;



    }

    @Override
    public List<BallotToCorrectionDTO> findAllBallots() {
        return ballotRepository.findAll().stream().
                map(x -> modelMapper.map(x, BallotToCorrectionDTO.class)).toList();
    }

}
