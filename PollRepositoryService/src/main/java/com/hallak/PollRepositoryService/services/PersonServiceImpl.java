package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import com.hallak.PollRepositoryService.security.JwtUtil;
import com.hallak.shared_library.dtos.PersonDTO;
import com.hallak.shared_library.dtos.PersonResponseDTO;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public PersonServiceImpl(JwtUtil jwtUtil, PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public PersonResponseDTO newPerson(PersonDTO personDTO) {
        if (personRepository.findByCpf(personDTO.cpf()).isPresent()){
            throw new EntityExistsException("Person already exists");
        }

        Person person = new Person(
                personDTO.cpf(), passwordEncoder.encode(personDTO.password()));
        personRepository.save(person);

        return new PersonResponseDTO(
                personDTO.cpf(), jwtUtil.generateToken(personDTO.cpf()));






    }
}
