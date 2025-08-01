package com.hallak.PollManagerService.services;


import com.hallak.PollManagerService.entities.PollDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    private final Queue queueToSaveNewPolls;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public PollManagerServiceImpl(Queue queueToSaveNewPolls, RabbitTemplate rabbitTemplate) {
        this.queueToSaveNewPolls = queueToSaveNewPolls;
        this.rabbitTemplate = rabbitTemplate;
    }


    public PollDTO newPoll(PollDTO pollDTO){
        rabbitTemplate.convertAndSend(queueToSaveNewPolls.getName(), pollDTO);
        return pollDTO;
    }




}
