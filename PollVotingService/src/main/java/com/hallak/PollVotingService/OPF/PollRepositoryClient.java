package com.hallak.PollVotingService.OPF;

import com.hallak.PollVotingService.OPF.config.FeignConfig;
import com.hallak.shared_library.dtos.PersonDTO;
import com.hallak.shared_library.dtos.PersonResponseDTO;
import com.hallak.shared_library.dtos.PollDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "pollRepository-service", configuration = FeignConfig.class)
public interface PollRepositoryClient {

    @GetMapping(value = "/api/")
    List<PollDTO> findAllPolls();

    @PostMapping(value = "/api/newPerson")
    PersonResponseDTO newPerson(@RequestBody PersonDTO personDTO);




}
