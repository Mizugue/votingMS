package com.hallak.PollRepositoryService.services;


import com.hallak.shared_library.dtos.PersonDTO;
import com.hallak.shared_library.dtos.PersonResponseDTO;

public interface PersonService {
    PersonResponseDTO newPerson(PersonDTO personDTO);
}
