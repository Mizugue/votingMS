package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.*;
import com.hallak.sharedDtos.dtos.BallotResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);
    BallotResponseDTO receiveBallot(BallotDTO ballotDTO);

}
