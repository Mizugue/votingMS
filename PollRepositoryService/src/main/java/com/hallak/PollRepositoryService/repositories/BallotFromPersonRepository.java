package com.hallak.PollRepositoryService.repositories;

import com.hallak.PollRepositoryService.entities.BallotFromPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotFromPersonRepository extends JpaRepository<BallotFromPerson, Long> {
    void deleteByPollId(Long pollId);
}
