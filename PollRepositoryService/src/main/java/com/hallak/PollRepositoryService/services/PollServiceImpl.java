package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.repositories.BallotRepository;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import com.hallak.shared_library.dtos.BallotToCorrectionDTO;
import com.hallak.shared_library.dtos.PollDTO;
import com.hallak.shared_library.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<PollDTO> list = pollRepository.findAll().stream().
                    map(x -> modelMapper.map(x, PollDTO.class)).toList();
        if (list.isEmpty()){
            throw new ResourceNotFoundException("No polls registered");
        }
        return list;
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
