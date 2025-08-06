package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.dtos.BallotDTO;
import com.hallak.PollRepositoryService.dtos.BallotToCorrectionDTO;
import com.hallak.PollRepositoryService.dtos.PersonDTO;
import com.hallak.PollRepositoryService.dtos.PollDTO;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface PollConsumerService {
    void receivePoll(PollDTO pollDTO);
    void receiveBallot(BallotDTO ballotDTO);
    void receivePerson(PersonDTO personDTO);
}
