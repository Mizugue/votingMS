package com.hallak.PollManagerService.services;




import com.hallak.PollManagerService.dtos.PollDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    private final String pollsQueueName;
    private final RabbitTemplate rabbitTemplate;


    @Autowired
    public PollManagerServiceImpl(@Value("${rabbitmq.queues.polls}") String pollsQueueName, RabbitTemplate rabbitTemplate) {
        this.pollsQueueName = pollsQueueName;
        this.rabbitTemplate = rabbitTemplate;
    }


    public PollDTO newPoll(PollDTO pollDTO){
        return (PollDTO) rabbitTemplate.convertSendAndReceive(pollsQueueName, pollDTO);
    }




}
