package com.hallak.PollRepositoryService.services;


import com.hallak.PollRepositoryService.dtos.PollDTO;
import com.hallak.PollRepositoryService.entities.Poll;
import com.hallak.PollRepositoryService.repositories.PollRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PollConsumerServiceImpl implements PollConsumerService {

    private final ModelMapper modelMapper;
    private final PollRepository pollRepository;

    @Autowired
    public PollConsumerServiceImpl(ModelMapper modelMapper, PollRepository pollRepository) {
        this.modelMapper = modelMapper;
        this.pollRepository = pollRepository;
    }

    @RabbitListener( queues = "${rabbitmq.queues.name}")
    public void receivePoll(@Payload PollDTO pollDTO){
        System.out.println("Recebido " + pollDTO);
        pollRepository.save(modelMapper.map(pollDTO, Poll.class));



    }







}