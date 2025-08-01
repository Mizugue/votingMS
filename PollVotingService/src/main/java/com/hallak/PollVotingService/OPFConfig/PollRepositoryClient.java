package com.hallak.PollVotingService.OPFConfig;

import com.hallak.PollVotingService.dtos.PollDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "pollRepository-service")
public interface PollRepositoryClient {

    @GetMapping(value = "/")
    List<PollDTO> findAllPolls();



}
