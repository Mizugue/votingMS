package com.hallak.PollVotingService.OPFConfig;

import com.hallak.sharedDtos.dtos.PersonDTO;
import com.hallak.sharedDtos.dtos.PersonResponseDTO;
import com.hallak.sharedDtos.dtos.PollDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pollRepository-service")
public interface PollRepositoryClient {

    @GetMapping(value = "/api/")
    List<PollDTO> findAllPolls();

    @PostMapping(value = "/api/newPerson")
    PersonResponseDTO newPerson(@RequestBody PersonDTO personDTO);




}
