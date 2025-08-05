package com.hallak.PollRepositoryService.repositories;

import com.hallak.PollRepositoryService.entities.Ballot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {
    boolean existsByVoterIdAndPollId(Long voterId, Long pollId);
    List<Ballot> findByPollId(Long pollId);

}
