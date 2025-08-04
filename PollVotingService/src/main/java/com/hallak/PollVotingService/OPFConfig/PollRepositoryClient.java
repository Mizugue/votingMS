package com.hallak.PollVotingService.OPFConfig;

import com.hallak.PollVotingService.dtos.BallotDTO;
import com.hallak.PollVotingService.dtos.PollDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pollRepository-service")
public interface PollRepositoryClient {

    @GetMapping(value = "/")
    List<PollDTO> findAllPolls();

    @PostMapping(value = "/")
    BallotDTO validateBallot(@RequestBody BallotDTO ballotDTO);




}
