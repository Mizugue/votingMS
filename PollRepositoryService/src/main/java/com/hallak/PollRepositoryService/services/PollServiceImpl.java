package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService{

    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;

    @Autowired
    public PollServiceImpl(ModelMapper modelMapper, PollRepository pollRepository){
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;

    }


    @Override
    public List<PollDTO> findAllPolls() {
        return pollRepository.findAll().stream().
                map(x -> modelMapper.map(x, PollDTO.class)).toList();
    }
}
