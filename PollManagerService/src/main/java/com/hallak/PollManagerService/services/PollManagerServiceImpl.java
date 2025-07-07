package com.hallak.PollManagerService.services;

import com.hallak.PollManagerService.PollManagerServiceApplication;
import com.hallak.PollManagerService.entities.PollDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    private final RabbitTemplate rabbitTemplate;
    private final AtomicLong idCounter = new AtomicLong();


    @Autowired
    public PollManagerServiceImpl(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;

    }

    public PollDTO newPoll(PollDTO pollDTO){
        PollDTO newPollDTO = new PollDTO(pollDTO.getName(), pollDTO.getOptions());
        newPollDTO.setId(idCounter.incrementAndGet());

        rabbitTemplate.convertAndSend("poll.exchange",
                "poll.tracking", newPollDTO);
        
        return newPollDTO;
    }




}
