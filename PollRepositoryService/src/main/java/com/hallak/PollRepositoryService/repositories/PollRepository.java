package com.hallak.PollRepositoryService.repositories;

import com.hallak.PollRepositoryService.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    @Query(
    value = "SELECT EXISTS(SELECT 1 FROM poll_options WHERE poll_id = :pollId AND option = :option)",
    nativeQuery = true
)
    boolean existsOptionInPoll(@Param("pollId") Long pollId, @Param("option") String option);

    Optional<Poll> findByName(String name);

}
