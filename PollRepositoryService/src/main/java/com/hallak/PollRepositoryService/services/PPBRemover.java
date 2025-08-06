package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PPBRemover {

    private final BallotRepository ballotRepository;
    private final PersonRepository personRepository;
    private final PollRepository pollRepository;

    @Autowired
    public PPBRemover(BallotRepository ballotRepository, PersonRepository personRepository, PollRepository pollRepository) {
        this.ballotRepository = ballotRepository;
        this.personRepository = personRepository;
        this.pollRepository = pollRepository;
    }

    public void deleteRecordAfterSettlement(Long pollId){
        pollRepository.deleteById(pollId);
        //personRepository.deleteByPollId(pollId);
        ballotRepository.deleteByPollId(pollId);
    }




}


