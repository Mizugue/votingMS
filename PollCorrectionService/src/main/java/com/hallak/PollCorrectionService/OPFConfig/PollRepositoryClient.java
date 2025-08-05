package com.hallak.PollCorrectionService.OPFConfig;

import com.hallak.PollCorrectionService.dto.BallotDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pollRepository-service")
public interface PollRepositoryClient {

    @GetMapping(value = "/api/ballots/{pollId}")
    List<BallotDTO> findAllBallotsByPollId(@PathVariable Long pollId);

    @GetMapping(value = "/api/ballots/pollName/{pollId}")
    String findPollNameByPollId(@PathVariable Long pollId);






}

