package com.hallak.PollCorrectionService.OPFConfig;


import com.hallak.shared_library.dtos.BallotDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "pollRepository-service")
public interface PollRepositoryClient {

    @GetMapping(value = "/api/ballots/{pollId}")
    List<BallotDTO> findAllBallotsByPollId(@PathVariable Long pollId);

    @GetMapping(value = "/api/ballots/pollName/{pollId}")
    String findPollNameByPollId(@PathVariable Long pollId);






}

