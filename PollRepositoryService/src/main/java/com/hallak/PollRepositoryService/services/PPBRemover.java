package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.repositories.BallotFromPersonRepository;
import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PPBRemover {

    private final BallotRepository ballotRepository;
    private final BallotFromPersonRepository ballotFromPersonRepository;
    private final PollRepository pollRepository;

    @Autowired
    public PPBRemover(BallotRepository ballotRepository, PersonRepository personRepository, PollRepository pollRepository, BallotFromPersonRepository ballotFromPersonRepository) {
        this.ballotRepository = ballotRepository;
        this.pollRepository = pollRepository;
        this.ballotFromPersonRepository = ballotFromPersonRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRecordAfterSettlement(Long pollId){
        pollRepository.deleteById(pollId);
        ballotRepository.deleteByPollId(pollId);
        ballotFromPersonRepository.deleteByPollId(pollId);
    }




}


