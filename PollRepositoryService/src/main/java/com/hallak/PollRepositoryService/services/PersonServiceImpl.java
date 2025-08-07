package com.hallak.PollRepositoryService.services;

import com.hallak.PollRepositoryService.entities.Person;
import com.hallak.PollRepositoryService.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Person insertPerson(Person person) {
        return personRepository.saveAndFlush(person);
        
    }
}
