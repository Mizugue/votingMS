package com.hallak.PollRepositoryService.repositories;

import com.hallak.PollRepositoryService.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCpf(String cpf);

}
