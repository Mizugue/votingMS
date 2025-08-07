package com.hallak.PollRepositoryService.services;

import com.hallak.sharedDtos.dtos.PersonDTO;
import com.hallak.sharedDtos.dtos.PersonResponseDTO;

public interface PersonService {
    PersonResponseDTO newPerson(PersonDTO personDTO);
}
