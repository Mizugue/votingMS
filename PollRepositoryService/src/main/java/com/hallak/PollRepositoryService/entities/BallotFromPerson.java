package com.hallak.PollRepositoryService.entities;

import com.hallak.PollRepositoryService.entities.toCompoundKey.BallotFromPersonID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(BallotFromPersonID.class)
public class BallotFromPerson {

    @Id
    @Column(name = "poll_id", nullable = false)
    private Long pollId;

    @Id
    @Column(name = "person_id", nullable = false)
    private Long personId;

    public BallotFromPerson(Long pollId, Long personId) {
        this.pollId = pollId;
        this.personId = personId;
    }

    public BallotFromPerson() {
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}