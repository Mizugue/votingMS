package com.hallak.PollManagerService.services;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.PollManagerService.dtos.PollDTOFromManager;
import com.hallak.shared_library.dtos.PollDTO;
import com.hallak.shared_library.exceptions.AsyncError;
import com.hallak.shared_library.exceptions.AsyncErrorException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class PollManagerServiceImpl implements PollManagerService {

    private final String pollsQueueName;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    @Autowired
    public PollManagerServiceImpl(@Value("${rabbitmq.queues.polls}") String pollsQueueName, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.pollsQueueName = pollsQueueName;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    public PollDTO newPoll(PollDTOFromManager pollDTO) {
        Object response = rabbitTemplate.convertSendAndReceive(pollsQueueName, pollDTO);
        System.out.println(response);
        System.out.println(response.getClass());

        if (response instanceof LinkedHashMap) {
            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) response;

            if (map.containsKey("id")) {
                return objectMapper.convertValue(map, PollDTO.class);
            }

            if (map.containsKey("error")) {
                throw new AsyncErrorException(objectMapper.convertValue(map, AsyncError.class));
            }

            throw new RuntimeException("Unknown response map structure");
        }

        throw new RuntimeException("Unexpected response type: " + response.getClass());
    }
}














