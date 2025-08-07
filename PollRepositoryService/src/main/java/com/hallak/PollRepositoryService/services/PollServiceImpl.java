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
    private final BallotRepository ballotRepository;
    private final PPBRemover ppbRemover;

    @Autowired
    public PollServiceImpl(ModelMapper modelMapper, PollRepository pollRepository, PersonRepository personRepository, BallotRepository ballotRepository, PPBRemover ppbRemover){
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
        this.ballotRepository = ballotRepository;
        this.ppbRemover = ppbRemover;

    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepository.findAll().stream().
                map(x -> modelMapper.map(x, PollDTO.class)).toList();
    }

    @Override
    public List<BallotToCorrectionDTO> findAllBallotsByPollId(Long pollId) {
        return ballotRepository.findByPollId(pollId).stream().
                map(x -> modelMapper.map(x, BallotToCorrectionDTO.class)).toList();
    }

    @Override
    public String findPollNameByPollId(Long pollId) {
        String name = pollRepository.findById(pollId).get().getName();
        //ppbRemover.deleteRecordAfterSettlement(pollId);
        return name;
    }



}
